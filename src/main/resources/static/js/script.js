var menuIcon=document.querySelector(".menu-icon");
var sideBar=document.querySelector(".sidebar");
var container=document.querySelector(".container")
menuIcon.onclick=function(){
    sideBar.classList.toggle("small-sidebar");
    container.classList.toggle("large-container")
}
document.addEventListener('DOMContentLoaded', function() {
    const menuBtns = document.querySelectorAll('.menu-btn');
    menuBtns.forEach(menuBtn => {
        menuBtn.addEventListener('click', function() {
            const menuContent = this.nextElementSibling;
            menuContent.style.display = menuContent.style.display === 'block' ? 'none' : 'block';
        });
    });

    // Fermer le menu si on clique en dehors
    window.addEventListener('click', function(event) {
        if (!event.target.matches('.menu-btn')) {
            const menus = document.querySelectorAll('.menu-content');
            menus.forEach(menu => {
                if (menu.style.display === 'block') {
                    menu.style.display = 'none';
                }
            });
        }
    });
});
document.addEventListener('DOMContentLoaded', function() {
    const userName = document.getElementById('username').value;
    console.log('Username:', userName);
    const canvas = document.getElementById('avatarCanvas');
    const context = canvas.getContext('2d');

    // Generate a random background color
    const bgColor = '#' + Math.floor(Math.random()*16777215).toString(16);

    // Draw background
    context.fillStyle = bgColor;
    context.fillRect(0, 0, canvas.width, canvas.height);

    // Draw the initial
    const initial = userName.charAt(0).toUpperCase();
    context.font = 'bold 25px Arial';
    context.fillStyle = 'white';
    context.textAlign = 'center';
    context.textBaseline = 'middle';
    context.fillText(initial, canvas.width / 2, canvas.height / 2);

    // Optional: Set the canvas content as the source of an image element if needed
    // const img = document.createElement('img');
    // img.src = canvas.toDataURL();
    // document.body.appendChild(img);
});
document.addEventListener('DOMContentLoaded', function() {
    const userName = document.getElementById('username').value;
    console.log('Username:', userName);
    const canvas = document.getElementById('avatarCanva');
    const context = canvas.getContext('2d');

    // Generate a random background color
    const bgColor = '#' + Math.floor(Math.random()*16777215).toString(16);

    // Draw background
    context.fillStyle = bgColor;
    context.fillRect(0, 0, canvas.width, canvas.height);

    // Draw the initial
    const initial = userName.charAt(0).toUpperCase();
    context.font = 'bold 25px Arial';
    context.fillStyle = 'white';
    context.textAlign = 'center';
    context.textBaseline = 'middle';
    context.fillText(initial, canvas.width / 2, canvas.height / 2);

    // Optional: Set the canvas content as the source of an image element if needed
    // const img = document.createElement('img');
    // img.src = canvas.toDataURL();
    // document.body.appendChild(img);
});

