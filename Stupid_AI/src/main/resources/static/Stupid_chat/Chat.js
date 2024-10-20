const conversations = document.querySelectorAll('.conver');

conversations.forEach(conver => {
    conver.addEventListener('click', () => {
        // Xóa lớp 'selected' khỏi tất cả các cuộc hội thoại
        conversations.forEach(c => c.classList.remove('selected'));
        // Thêm lớp 'selected' vào cuộc hội thoại được chọn
        conver.classList.add('selected');
    });
});