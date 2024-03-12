import express from 'express';
const router = express.Router();

// controllers
import { onGetAllUsers, onCreateUser, onGetUserById, onDeleteUserById } from '../controllers/user.js';

router.get('/', onGetAllUsers)
router.post('/', onCreateUser)
router.get('/:id', onGetUserById)
router.delete('/:id', onDeleteUserById)

export default router;
