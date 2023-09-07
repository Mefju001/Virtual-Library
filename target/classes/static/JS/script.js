document.getElementById("start").addEventListener('click',funkcja1)
document.getElementById("start1").addEventListener('click',funkcja1)

document.getElementById("confirm-but").addEventListener('click',funkcja3)

function funkcja1(){
    document.getElementById("menu1").style.display="none";
    document.getElementById("dodaj").style.display="flex";
}
function funkcja3(){
    document.getElementById("menu1").style.display="block";
    document.getElementById("dodaj").style.display="none";
}

