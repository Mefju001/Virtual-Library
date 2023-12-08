document.addEventListener('DOMContentLoaded', function() {
    const submenuToggle = document.getElementById('submenu-toggle');
    const submenuContent = document.querySelector('.submenu-content');

    submenuToggle.addEventListener('change', function() {
        if (this.checked) {
            submenuContent.style.display = 'block';
        } else {
            submenuContent.style.display = 'none';
        }
    });

    const submenu2Toggle = document.getElementById('submenu2-toggle');
    const submenu2Content = document.querySelector('.submenu2-content');

    submenu2Toggle.addEventListener('change', function() {
        if (this.checked) {
            submenu2Content.style.display = 'block';
        } else {
            submenu2Content.style.display = 'none';
        }
    });
    const submenu3Toggle = document.getElementById('submenu3-toggle');
    const submenu3Content = document.querySelector('.submenu3-content');

    submenu3Toggle.addEventListener('change', function() {
        if (this.checked) {
            submenu3Content.style.display = 'block';
        } else {
            submenu3Content.style.display = 'none';
        }
    });
});
