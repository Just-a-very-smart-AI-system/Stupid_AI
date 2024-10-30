document.addEventListener("DOMContentLoaded", function () {
    const todayContainer = document.querySelector(".conversations .conver_today");
    const beforeContainer = document.querySelector(".conversations .conver_before");
    const inputField = document.querySelector(".text");
    const sendButton = document.querySelector(".icon_send");
    const messageContent = document.querySelector(".message_content");

    let converId = 0;

    // Ẩn nút sendButton khi bắt đầu
    sendButton.style.display = "none";

    // Hàm để lấy tất cả các cuộc trò chuyện
    function loadConversations() {
        // Thêm nút "New Chat" vào conver_today
        const newChatDiv = document.createElement("div");
        newChatDiv.classList.add("conver");
        newChatDiv.innerHTML = `
            <p class="newChat">New Chat</p>
        `;
        newChatDiv.addEventListener("click", function() {
            // Logic để tạo cuộc trò chuyện mới
            messageContent.innerHTML = "";
            converId = 0;
        });

        todayContainer.appendChild(newChatDiv); 
        fetch("http://localhost:8080/conversations/all")
            .then(response => {
                if (!response.ok) {
                    throw new Error("Network response was not ok");
                }
                return response.json();
            })
            .then(data => {
                const today = new Date();
                const startOfDay = new Date(today.getFullYear(), today.getMonth(), today.getDate());
                const endOfDay = new Date(today.getFullYear(), today.getMonth(), today.getDate() + 1);
                
                data.sort((a, b) => new Date(b.timeChat) - new Date(a.timeChat));

                data.forEach(conversation => {
                    const conversationTime = new Date(conversation.timeChat);
                    const converDiv = document.createElement("div");
                    converDiv.classList.add("conver");
                    converDiv.innerHTML = `
                        <p class="newChat">${conversation.name}</p>
                        <span class="ellipsis">...</span>
                    `;

                    converDiv.addEventListener("click", function () {
                        LoadConver(conversation.id);
                        converId = conversation.id;
                    });

                    if (conversationTime >= startOfDay && conversationTime < endOfDay) {
                        todayContainer.appendChild(converDiv);
                    } else {
                        beforeContainer.appendChild(converDiv);
                    }
                });

            })
            .catch(error => console.error("Error fetching conversations:", error));
    }

    loadConversations();

    function LoadConver(id) {
        fetch(`http://localhost:8080/messages/conver/${id}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error("Network response was not ok");
                }
                return response.json();
            })
            .then(messages => {
                const messageContent = document.querySelector(".message_content");
                messageContent.innerHTML = "";
                console.log(messages);

                messages.forEach(message => {
                    const userDiv = document.createElement("div");
                    userDiv.classList.add("ques");
                    userDiv.innerHTML = `<p>${formatText(message.ques)}</p>`;

                    const botDiv = document.createElement("div");
                    botDiv.classList.add("ans");
                    botDiv.innerHTML = `<p>${formatText(message.ans)}</p>`;

                    messageContent.appendChild(userDiv);
                    messageContent.appendChild(botDiv);
                });
            })
            .catch(error => console.error("Error fetching messages:", error));
    }

    function sendMessage() {
        const prompt = inputField.value;
        if (prompt.trim() === "") return;
        console.log(prompt);
        
        if (converId != 0) {
            getMess(converId, prompt);
        } else {
            createConver(prompt, prompt); // Truyền tên và prompt để tạo cuộc trò chuyện mới

            const converDiv = document.createElement("div");
            converDiv.classList.add("conver");
            converDiv.innerHTML = `
                <p class="newChat">${prompt}</p>
                <span class="ellipsis">...</span>
            `;

            todayContainer.appendChild(converDiv);
        }

    }

    function createConver(name, prompt) {
        fetch("http://localhost:8080/conversations/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(name) 
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Network response was not ok");
                }
                return response.json();
            })
            .then(conversation => {
                console.log("Conversation added:", conversation);
                converId = conversation.id; // cập nhật converId với cuộc trò chuyện mới
                getMess(conversation.id, prompt); // truyền prompt vào hàm getMess
            })
            .catch(error => console.error("Error adding conversation:", error));
    }

    function getMess(converId, prompt) {
        fetch(`http://localhost:8080/messages/add/${converId}`, {
            method: "POST", 
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(prompt)
        })
        .then(response => {
            const contentType = response.headers.get("content-type");
            if (contentType && contentType.includes("application/json")) {
                return response.json();
            } else {
                return response.text();
            }
        })
        .then(data => {
            const responseText = typeof data === 'string' ? data : data.message;
            const botDiv = document.createElement("div");
            botDiv.classList.add("ans");
            botDiv.style.whiteSpace = "pre-wrap";
            messageContent.appendChild(botDiv); // Thêm botDiv vào messageContent trước

            // Hàm để hiện thị văn bản ra từ từ
            function typeText(text, element, delay) {
                let index = 0;
                const interval = setInterval(() => {
                    if (index < text.length) {
                        element.innerHTML = text.substring(0, index + 1);
                        index++;
                        // Cuộn xuống dưới
                        messageContent.scrollTop = messageContent.scrollHeight;
                    } else {
                        clearInterval(interval);
                    }
                }, delay);
            }

            const formattedResponse = formatText(responseText);
            typeText(formattedResponse, botDiv, 15); 

            
        })
        .catch(error => console.error("Error:", error));
    }

    function formatText(input) {
        let formattedText = input.replace(/\n/g, '<br>');
        formattedText = formattedText.replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>');
        
        formattedText = formattedText.replace(/\*(.*?)\*/g, '<em>$1</em>');
        
        formattedText = formattedText.replace(/__(.*?)__/g, '<u>$1</u>');
        
        return formattedText;
    }
    
    function show_ques() {
        const ques = inputField.value;
        const userDiv = document.createElement("div");

        if (ques.trim() === "") return;
        userDiv.classList.add("ques");
        userDiv.innerHTML = `<p>${ques}</p>`;
        messageContent.appendChild(userDiv);
        // Cuộn xuống dưới
        messageContent.scrollTop = messageContent.scrollHeight;
        inputField.value = ""; // Xóa trường sau khi gửi tin nhắn
    }

    sendButton.addEventListener("click", function () {
        sendMessage();
        show_ques();
    });

    inputField.addEventListener("input", function () {
        // Hiển thị nút sendButton khi có nội dung trong inputField
        if (inputField.value.trim() !== "") {
            sendButton.style.display = "block"; // Hiển thị nút
        } else {
            sendButton.style.display = "none"; // Ẩn nút
        }
    });

    inputField.addEventListener("keypress", function (event) {
        if (event.key === "Enter") {
            sendMessage();
            show_ques();
        }
    });
});
