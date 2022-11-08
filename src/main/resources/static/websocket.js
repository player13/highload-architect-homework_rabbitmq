const StompWebsocketState = Object.freeze({
    UNKNOWN: Symbol('Unknown'),
    CONNECTED: Symbol('Connected'),
    DISCONNECTED: Symbol('Disconnected')
})

const StompWebSocket = (url, topic, stateChangedCallback = () => {}, messageReceivedCallback = () => {}) => {

    let stompClient = null;

    const connect = () => {
        stateChangedCallback(StompWebsocketState.UNKNOWN);
        const socket = new SockJS(url);
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected);
    }

    const disconnect = () => {
        stateChangedCallback(StompWebsocketState.UNKNOWN);
        if (stompClient != null) {
            stompClient.disconnect(onDisconnected);
        }
    }

    const onConnected = (frame) => {
        stateChangedCallback(StompWebsocketState.CONNECTED);
        stompClient.subscribe(topic, (message) => {
            messageReceivedCallback(JSON.parse(message.body));
        });
        console.log('Connected: ' + frame);
    }

    const onDisconnected = () => {
        stompClient = null;
        stateChangedCallback(StompWebsocketState.DISCONNECTED);
        console.log('Disconnected');
    }

    return Object.freeze({ connect, disconnect });
}
