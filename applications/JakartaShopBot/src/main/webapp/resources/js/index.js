var socket = new WebSocket('ws://localhost:8080/JakartaShopBot-1.0-SNAPSHOT/websocket');
function scrollToBottom() {
    var chatContainer = document.getElementById('chat-area');
    chatContainer.scrollTop = chatContainer.scrollHeight;
}

function sendMessageviaWs(){
    message = document.getElementById('input-message').value;
    addMessage(message, 'right');
    document.getElementById('input-message').value = '';
    socket.send(message);

}
socket.onmessage = function(event) {
    console.log('Received: ' + event.data);
    appendToLeftMessage(event.data);
};

function addMessage(message) {
    var chatArea = document.getElementById('chat-area');
    var messageDiv = document.createElement('div');
    messageDiv.className = 'message right';
    messageDiv.textContent = message;
    chatArea.appendChild(messageDiv);
    messageDiv = document.createElement('div');
    messageDiv.className = 'message left';
    messageDiv.textContent = '';
    chatArea.appendChild(messageDiv);
    scrollToBottom();
}

function appendToLeftMessage(message) {
    var chatArea = document.getElementById('chat-area');
    var leftMessageDiv = chatArea.querySelector('.message.left:last-of-type');
    leftMessageDiv.innerHTML += message;
    scrollToBottom();
}