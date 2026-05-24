
function insertAtCursor(textarea, text) {
    easyMDE.codemirror.replaceSelection(text);
}

document.addEventListener("paste", async (e) => {
    const items = e.clipboardData.items;
    const input = document.getElementById("editor");

    for (let item of items) {
        if (item.type.startsWith("image")) {
            e.preventDefault();

            const file = item.getAsFile();

            const formData = new FormData();
            formData.append("file", file);

            const res = await fetch(`${API_BASE_URL}admin/projects/uploadImage`, {
                method: "POST",
                body: formData,
                headers: {
                    "Authorization": `Bearer ${localStorage.getItem("token")}`
                },
            });

            const url = await res.text();

            insertAtCursor(input, url);
        }
    }
});

const textarea = document.getElementById("editor");
const preview = document.getElementById("preview");
const easyMDE = new EasyMDE({element: textarea});

function updatePreview() {
    const md = easyMDE.value();
    preview.innerHTML = marked.parse(md);
}

easyMDE.codemirror.on("change", updatePreview);

async function onUploadProjectSubmit(e) {
    e.preventDefault();

    const title = document.getElementById("title").value;
    const date = document.getElementById("date").value;
    const timeperiod = document.getElementById("timeperiod").value;
    const languages = document.getElementById("languages").value;
    const thumbnail = document.getElementById("thumbnail").value;
    const description = document.getElementById("description").value;
    let github = document.getElementById("github").value;
    let youtube = document.getElementById("youtube").value;
    let liveDemo = document.getElementById("demo").value;
    
    github = github.trim() === "" ? null : github;
    youtube = youtube.trim() === "" ? null : youtube;
    liveDemo = liveDemo.trim() === "" ? null : liveDemo;

    const result = await uploadProject(title, description, languages, timeperiod, date, thumbnail, [marked.parse(easyMDE.value())], github, youtube, liveDemo);
    if("error" in result) {
        alert(`Failed to upload projects: ${result.error}`);
        return;
    }

    window.location.href = "index.html";
}