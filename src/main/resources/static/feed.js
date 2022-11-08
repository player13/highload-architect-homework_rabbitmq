const Feed = () => {
    let webSocket = null;
    const connect = () => {
        webSocket = createStompWebSocket();
        webSocket.connect();
    }
    const disconnect = () => {
      if (webSocket != null) {
          webSocket.disconnect();
      }
    }
    return Object.freeze({ connect, disconnect });
}

const createStompWebSocket = () => {
    const follower = document.getElementById('follower').value;
    return StompWebSocket('/websocket', '/exchange/feed/' + follower, onWebSocketStateChange, showFeedPost);
}

const onWebSocketStateChange = (state) => {
    switch (state) {
        case StompWebsocketState.CONNECTED:
            showFeed();
            break;
        case StompWebsocketState.DISCONNECTED:
        case StompWebsocketState.UNKNOWN:
            hideFeed();
            break;
    }
}

const showFeed = () => {
    const follower = document.getElementById('follower');
    follower.disabled = true
    document.getElementById('connect').disabled = true;
    document.getElementById('disconnect').disabled = false;
    document.getElementById('feed').style.visibility = 'visible';
    loadFeed(follower.value);
}

const hideFeed = () => {
    document.getElementById('feed').style.visibility = 'hidden';
    document.getElementById('feed').innerHTML = '';
    document.getElementById('connect').disabled = false;
    document.getElementById('disconnect').disabled = true;
    document.getElementById('follower').disabled = false;
}

const loadFeed = (follower) => {
    const showFeed = (feed) => {
        feed.forEach(post => {
            showFeedPost(post);
        })
    }
    doRequest('GET', '/feeds/' + follower + '/posts', {}, null, showFeed);
}

const showFeedPost = (post) => {
    const feed = document.getElementById('feed');
    if (feed.childElementCount > 9) {
        feed.removeChild(feed.lastElementChild);
    }
    const div = document.createElement('div');
    const header = document.createElement('p');
    header.appendChild(document.createTextNode(post.author + ', ' + post.title));
    const content = document.createElement('p');
    content.style.wordBreak = 'break-word';
    content.style.overflowWrap = 'break-word';
    content.appendChild(document.createTextNode(post.content));
    div.append(header, content);
    feed.insertBefore(div, feed.firstElementChild);
}

const feed = Feed();
