

function redirectTo(url) {
    window.location.href = url;
}



function redirectToAction(url, action) {
    window.location.href = url + action;
}

function redirectToAnalysis(gameId) {
    // Thay đổi URL để chuyển hướng đến trang phân tích
    window.location.href = 'analysis?gameId=' + gameId; // Thay đổi URL phù hợp
}



