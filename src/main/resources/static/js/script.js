console.log(typeof openPopup);
function openPopup() {
    document.getElementById('popup').style.display = 'block';
    document.getElementById('overlay').style.display = 'block';
}

function openPopupDeposit() {
    document.getElementById('popup-deposit').style.display = 'block';
    document.getElementById('overlay').style.display = 'block';
}


function closePopupDeposit() {
    document.getElementById('popup-deposit').style.display = 'none';
    document.getElementById('overlay').style.display = 'none';
}

function closePopup() {
    document.getElementById('popup').style.display = 'none';
    document.getElementById('overlay').style.display = 'none';
}
