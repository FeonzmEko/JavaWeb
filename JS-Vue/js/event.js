import { PrintLog } from "./utils.js";

const rows = document.querySelectorAll("table tbody tr");
rows.forEach(row => {
    row.addEventListener("mouseenter", () => {
        row.style.backgroundColor = "lightpink";
    });
    row.addEventListener("mouseleave", () => {
        row.style.backgroundColor = "";
    });

    // 2. 双击行 -> 高亮黄色
    row.addEventListener("dblclick", () => {
        row.style.backgroundColor = "lightyellow";
    });

});

const message1 = document.getElementById("message");
const nameCells = document.querySelectorAll('.name');
nameCells.forEach(cell => {
    cell.addEventListener('click', () => {
        message1.textContent = "你点击了姓名: " + cell.textContent;
    });
})

// 3. 按钮点击
const buttons = document.querySelectorAll("table button");
buttons.forEach(btn => {
    btn.addEventListener("click", () => {
        alert("你点击了按钮！");
    });
});

// 4. 输入框回车
const inputBox = document.getElementById("inputBox");
const message = document.getElementById("message");
inputBox.addEventListener("keydown", (e) => {
    if (e.key === "Enter") {
        message.textContent = "你输入了: " + inputBox.value;
        inputBox.value = "";
    }
});

// 5. 方向键提示
document.addEventListener("keydown", (e) => {
    if (["ArrowUp", "ArrowDown", "ArrowLeft", "ArrowRight"].includes(e.key)) {
        message.textContent = "你按下了方向键: " + e.key;
    }
});