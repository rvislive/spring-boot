import express from 'express';
// controllers
import { getRecentConversation, getConversationByRoomId, initiate, postMessage, markConversationReadByRoomId } from '../controllers/chatRoom.js';

const router = express.Router();

router.get('/', getRecentConversation)
router.get('/:roomId', getConversationByRoomId)
router.post('/initiate', initiate)
router.post('/:roomId/message', postMessage)
router.put('/:roomId/mark-read', markConversationReadByRoomId)

export default router;
