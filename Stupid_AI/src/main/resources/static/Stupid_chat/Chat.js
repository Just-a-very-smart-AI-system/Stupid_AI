document.addEventListener("DOMContentLoaded", function() {
    const todayContainer = document.querySelector(".conversations .conver_today");
    const beforeContainer = document.querySelector(".conversations .conver_before");

    // Hàm để lấy tất cả các cuộc trò chuyện
    function loadConversations() {
        fetch("http://localhost:8080/conversations/all") 
            .then(response => {
                if (!response.ok) {
                    throw new Error("Network response was not ok");
                }
                return response.json(); // Chuyển đổi phản hồi thành JSON
            })
            .then(data => {
                const today = new Date(); // Lấy ngày hiện tại
                const startOfDay = new Date(today.getFullYear(), today.getMonth(), today.getDate());
                const endOfDay = new Date(today.getFullYear(), today.getMonth(), today.getDate() + 1);

                // Duyệt qua danh sách cuộc trò chuyện và tạo phần tử HTML
                data.forEach(conversation => {
                    const conversationTime = new Date(conversation.timeChat); // Chuyển đổi thời gian cuộc trò chuyện thành đối tượng Date
                    const converDiv = document.createElement("div");
                    converDiv.classList.add("conver");
                    converDiv.innerHTML = `
                        <p class="newChat">${conversation.name}</p>
                        <span class="ellipsis">...</span>
                    `;

                    // Thêm sự kiện lắng nghe cho converDiv
                    converDiv.addEventListener("click", function() {
                        LoadCover(conversation.id); // Gọi hàm LoadCover với ID của cuộc trò chuyện
                    });

                    // Kiểm tra xem cuộc trò chuyện có nằm trong ngày hôm nay không
                    if (conversationTime >= startOfDay && conversationTime < endOfDay) {
                        todayContainer.appendChild(converDiv); // Thêm vào conver_today
                    } else {
                        beforeContainer.appendChild(converDiv); // Thêm vào conver_before
                    }
                });
            })
            .catch(error => console.error("Error fetching conversations:", error));
    }

    loadConversations();
    
    function LoadCover(id) {
        fetch(`http://localhost:8080/messages/conver/${id}`) // Gọi API với ID
            .then(response => {
                if (!response.ok) {
                    throw new Error("Network response was not ok");
                }
                return response.json(); // Chuyển đổi phản hồi thành JSON
            })
            .then(messages => {
                const messageContent = document.querySelector(".message_content");
                messageContent.innerHTML = ""; // Xóa nội dung cũ trước khi thêm mới
    
                // Duyệt qua danh sách tin nhắn và tạo phần tử HTML
                messages.forEach(message => {
                    const userDiv = document.createElement("div");
                    userDiv.classList.add("ques");
                    userDiv.innerHTML = `<p>${message.question}</p>`; // Giả sử message có thuộc tính question
    
                    const botDiv = document.createElement("div");
                    botDiv.classList.add("ans");
                    botDiv.innerHTML = `<p>${message.answer}</p>`; // Giả sử message có thuộc tính answer
    
                    messageContent.appendChild(userDiv); // Thêm câu hỏi vào message_content
                    messageContent.appendChild(botDiv); // Thêm câu trả lời vào message_content
                });
            })
            .catch(error => console.error("Error fetching messages:", error));
    }
    const inputField = document.querySelector(".text");
    const sendButton = document.querySelector(".icon_send");
    const messageContent = document.querySelector(".message_content");

    function sendMessage() {
        const prompt = inputField.value;
        if (prompt.trim() === "") return; // Không gửi nếu trường rỗng
        // Gọi API
        fetch("http://localhost:8080/api/chat", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(prompt)
        })
        .then(response => {
            // Kiểm tra xem phản hồi có phải là JSON không
            const contentType = response.headers.get("content-type");
            if (contentType && contentType.includes("application/json")) {
                return response.json(); // Nếu là JSON, chuyển đổi
            } else {
                return response.text(); // Nếu không, chuyển đổi thành văn bản
            }
        })
        .then(data => {
            // Xử lý dữ liệu trả về từ API
            const responseText = typeof data === 'string' ? data : data.message; // Lấy thông điệp từ đối tượng JSON
            const botDiv = document.createElement("div");
            botDiv.classList.add("ans");
            botDiv.innerHTML = `<p>${responseText}</p>`;
            messageContent.appendChild(botDiv);
        })
        .catch(error => console.error("Error:", error));
    }
            
    function show_ques(){
        const ques = inputField.value;
        const userDiv = document.createElement("div");

        if (ques.trim() === "") return; // Không gửi nếu trường rỗng
        userDiv.classList.add("ques");
        userDiv.innerHTML = `<p>${ques}</p>`;
        messageContent.appendChild(userDiv);
        inputField.value = "";
    }
    sendButton.addEventListener("click", function(){
        sendMessage();
        console.log("Send");
        show_ques();

    });
    inputField.addEventListener("keypress", function(event) {
        if (event.key === "Enter") {
            sendMessage();
            console.log("Enter");
            show_ques();
        }
    });
});
