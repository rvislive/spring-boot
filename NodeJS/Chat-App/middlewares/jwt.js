import jwt from 'jsonwebtoken';
// models
import UserModel from '../models/User.js';

const SECRET_KEY = 'some-secret-key';

export const decode = async (req, res, next) => {
  try {
      const authHeader = req.headers.authorization;
      // is header there? 
      if(!authHeader) {
          return res.status(401).json({
              message: "Auth token is required.",
              status_code: 401,
              status: false,
              error: 'Authorization failed.'
          })
      } else {
          let result;
          const token = authHeader.split(' ')[1]; // using bearer token.
          result = jwt.verify(token, SECRET_KEY);

          req.userId = result.userId;
          // checking, is user there? 
          const isValidUser = await UserModel.findOne({
              _id: req.userId
          }).select('_id type firstName lastName');

          // token expiry time 30 days. 
          

          if(!isValidUser) {
              return res.status(200).json({
                  message: 'Please login with vaild credentials',
                  status_code: 200,
                  status: false
              })
          }

          req.user = isValidUser;
          req.token = token
          next();
      }
  } catch (error) {
      throw error;
  }
}

// just shortcut login
export const encode = async (req, res, next) => {
  try {
    const { userId } = req.params;
    const user = await UserModel.getUserById(userId);
    const payload = {
      userId: user._id,
      userType: user.type,
    };
    const authToken = jwt.sign(payload, SECRET_KEY);
    req.authToken = authToken;
    next();
  } catch (error) {
    return res.status(400).json({ success: false, message: error.error });
  }
}
