const publicPost = () => {
    const author = document.getElementById('author').value;
    const title = document.getElementById('title').value;
    const content = document.getElementById('content').value;
    doRequest("POST", "/posts", {}, { author, title, content });
}

const follow = () => {
    const authorToFollow = document.getElementById('authorToFollow').value;
    const follower = document.getElementById('follower').value;
    doRequest("POST", "/subscriptions", {}, { author: authorToFollow, follower });
}
