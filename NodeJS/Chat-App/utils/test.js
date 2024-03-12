

const users = [];
const connnections = [];

//listen on every connection
io.on('connection', socket => {
    console.log('A new user is connected');

    //add the new socket to the connections array
    connnections.push(socket)

    //Set the first username of the user as 'Anonymous'
    socket.username = 'Anonymous';

    //listen on change_username
    socket.on('change_username', data => {
        let id = socket._id;
        socket.id = id;
        socket.username = data.firstName;
        users.push({id, username: socket.username});
        updateUsernames();
    })

    //update Usernames in the client
    const updateUsernames = () => {
        io.sockets.emit('get users', users)
    }

    //listen on typing
    socket.on('typing', data => {
        socket.username = data.firstName;
        socket.broadcast.emit('typing', {username: socket.username})
    })

    //listen on new_message *TESTED
    socket.on('new_message', data => {
        //broadcast the new message
        io.sockets.emit('new_message', {
            message : data.message, 
            username : socket.username 
        });
    })

    //Disconnect
    socket.on('disconnect', data => {
        socket.username = data.firstName;
        if(!socket.username)
            return;
        //find the user and delete from the users list
        let user = undefined;
        for(let i= 0; i<users.length; i++){
            if(users[i].id === socket.id){
                user = users[i];
                break;
            }
        }
        users.splice(user,1);
        //Update the users list
        updateUsernames();
        connnections.splice(connnections.indexOf(socket), 1);
    })
})
