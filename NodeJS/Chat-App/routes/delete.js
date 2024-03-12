import express from 'express';
const router = express.Router();

// controllers
import { deleteRoomById, deleteMessageById } from '../controllers/delete.js';

router.delete('/room/:roomId', deleteRoomById)
router.delete('/message/:messageId', deleteMessageById)

export default router;
