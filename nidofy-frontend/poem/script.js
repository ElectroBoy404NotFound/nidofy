
function showTimedAlert(message, timeout = 3000, color="info") {
    const alertId = `alert-${Date.now()}`;
    const alertHTML = `
        <div id="${alertId}" class="alert alert-${color} alert-dismissible mb-2 position-relative alert-slide" role="alert" style="min-width: 300px;">
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            <div class="progress position-absolute bottom-0 start-0 w-100" style="height: 4px;">
                <div class="progress-bar bg-${color}" role="progressbar" style="width: 100%; transition: width ${timeout}ms linear;"></div>
            </div>
        </div>
    `;

    document.getElementById("alert-container").insertAdjacentHTML("beforeend", alertHTML);

    const alertElement = document.getElementById(alertId);
    const progressBar = alertElement.querySelector('.progress-bar');

    setTimeout(() => {
        alertElement.classList.add("showing");
    }, 10);

    setTimeout(() => {
        progressBar.style.width = '0%';
    }, 50);

    setTimeout(() => {
        alertElement.classList.remove("showing");
        alertElement.classList.add("hiding");

        setTimeout(() => {
            alertElement.remove();
        }, 300);
    }, timeout);
}

async function copyPoem() {
    const title = document.getElementById("title").innerText;
    const date = dateStringToNumerialString(document.getElementById("date").innerText);
    const signatureElement = document.getElementById("signature");
    const signature = signatureElement.innerText.replace("~", "");

    const header = `"${title}" by${signature}, ${date}\n\n`;

    let poemBody = "";
    document.getElementById("poem").querySelectorAll("p").forEach(p => {
        if(p.id === "signature") return;
        poemBody += p.innerText + "\n";
    });

    poemBody += `      ${'-'.repeat(signatureElement.dataset.number)}${signature}`;

    const finalPoem = header + poemBody;
    try {
        await navigator.clipboard.writeText(finalPoem);
        showTimedAlert("Poem copied to clipboard!", 2000, "info");
    } catch(err) {
        showTimedAlert("Failed to copy to clipboard!", 3000, "danger");
    }
}

window.addEventListener("DOMContentLoaded", async function () {
    const urlParams = new URLSearchParams(window.location.search);
    const requestedId = urlParams.get('id');

    if(!requestedId) {
        localStorage.setItem("error", "No poem ID provided.");
        window.location.href = "/poetry/";
        return;
    }

    let poem = await getPoem(requestedId);

    if("error" in poem) {
        if(poem.error === 1003) {
            localStorage.setItem("error", "Poem not found.");
            window.location.href = "/poetry/";
            return;
        }

        if(poem.error === 9999) {
            localStorage.setItem("error", "Invalid poem ID.");
            window.location.href = "/poetry/";
            return;
        }

        localStorage.setItem("error", poem.error);
        window.location.href = "/poetry/";
        return;
    }

    document.title = `Nidofy - ${poem.title}`;

    document.getElementById("title").innerText = poem.title;
    document.getElementById("date").innerText = poem.date;

    const poemContainer = document.getElementById("poem");

    const poemContent = poem.poem;
    for (let stanza of poemContent) {
        let element = document.createElement("p");
        for (let line of stanza) {
            element.appendChild(document.createTextNode(line));
            element.appendChild(document.createElement("br"));
        }
        element.classList.add("card-text");
        poemContainer.appendChild(element);
    }

    let authorElement = document.createElement("p");
    authorElement.classList.add("card-text");
    authorElement.classList.add("text-muted");
    authorElement.classList.add("text-end");
    authorElement.id = "signature";
    authorElement.dataset.number = poem.signatureLength;
    authorElement.appendChild(document.createTextNode(`~ ${poem.signature}`));

    poemContainer.appendChild(authorElement);
});