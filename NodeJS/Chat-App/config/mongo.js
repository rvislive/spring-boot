import mongoose from 'mongoose'
import config from './index.js'

const CONNECTION_URL = `mongodb://${config.db.url}/${config.db.name}`;

const getConnection = async (req, res) => {
  try {
    await mongoose.connect(CONNECTION_URL, {
        useNewUrlParser: true,
        useUnifiedTopology: true,
        useCreateIndex: true
    })
    console.log("Database Connected");
  } catch (error) {
    isError(res, {
        err: error
    })
  }
}

getConnection();
