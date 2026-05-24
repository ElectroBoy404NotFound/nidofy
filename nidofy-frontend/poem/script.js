
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
    authorElement.appendChild(document.createTextNode(`~ ${poem.signature}`));

    poemContainer.appendChild(authorElement);
});