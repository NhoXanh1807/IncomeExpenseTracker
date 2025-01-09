const changeImgBtn = document.getElementById('change-img-btn'); // Nút đổi ảnh
const userImgMain = document.querySelector('.profile .main .modify-image img'); // Ảnh trong .modify-image
const userImgProfile = document.querySelector('#input-profile .modify-image img'); // Ảnh trong #input-profile
const dashimg = document.querySelector('.profile .sidebar .sidebar-user .modify-image img')
changeImgBtn.addEventListener('click', () => {
    // Hiển thị prompt cho người dùng nhập URL
    const imageUrl = prompt("Enter the URL of the new image:");

    // Kiểm tra nếu URL hợp lệ
    if (imageUrl && imageUrl.trim() !== "") {
        const trimmedUrl = imageUrl.trim();
        // Cập nhật cả hai ảnh
        userImgMain.src = trimmedUrl;
        userImgProfile.src = trimmedUrl;
        dashimg.src=trimmedUrl;
    } else {
        alert("No URL entered or invalid URL!");
    }
});