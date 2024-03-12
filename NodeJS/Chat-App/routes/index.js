import express from 'express';
// middlewares
import { encode } from '../middlewares/jwt.js';

const router = express.Router();

// this flow is just to login by userID only. and getting the jwt token.
router.post('/login/:userId', encode, (req, res, next) => {
    return res.status(200).json({
        success: true,
        authorization: req.authToken,
    });
});

export default router;
