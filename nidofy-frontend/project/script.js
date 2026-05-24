
window.addEventListener("DOMContentLoaded", async function () {
    const urlParams = new URLSearchParams(window.location.search);
    const requestedId = urlParams.get('id');

    if(!requestedId) {
        localStorage.setItem("error", "No project ID provided.");
        window.location.href = "/project/";
        return;
    }

    let project = await getProject(requestedId);

    // if("error" in project) {
    //     if(project.error === 1003) {
    //         localStorage.setItem("error", "project not found.");
    //         window.location.href = "/project/";
    //         return;
    //     }

    //     if(project.error === 9999) {
    //         localStorage.setItem("error", "Invalid project ID.");
    //         window.location.href = "/project/";
    //         return;
    //     }

    //     localStorage.setItem("error", project.error);
    //     window.location.href = "/project/";
    //     return;
    // }

    document.title = `Nidofy - ${project.title}`;

    document.getElementById("title").innerText = project.title;
    document.getElementById("date").innerText = project.date;
    document.getElementById("timeperiod").innerText = project.timeperiod;
    document.getElementById("lnf").innerText = project.languages;

    const projectContainer = document.getElementById("project");

    // const poemContent = poem.poem;
    // for (let stanza of poemContent) {
    //     let element = document.createElement("p");
    //     for (let line of stanza) {
    //         element.appendChild(document.createTextNode(line));
    //         element.appendChild(document.createElement("br"));
    //     }
    //     element.classList.add("card-text");
    //     poemContainer.appendChild(element);
    // }

    const thumbnailElement = document.createElement("img");
    thumbnailElement.src = project.thumbnail;
    thumbnailElement.alt = `${project.title} thumbnail`;
    thumbnailElement.classList.add("d-block");
    thumbnailElement.classList.add("mx-auto");
    thumbnailElement.classList.add("mb-3");
    projectContainer.appendChild(thumbnailElement);

    const projectContent = project.explanation;
    for(let paragraph of projectContent) {
        let element = document.createElement("div");
        element.innerHTML = paragraph;
        element.classList.add("card-text");
        projectContainer.appendChild(element);
        projectContainer.appendChild(document.createElement("br"));
    }

    const mediaContainer = document.getElementById("project-footer");
    let mediaElement = document.createElement("div");

    mediaElement.classList.add("d-flex");
    mediaElement.classList.add("gap-2");
    mediaElement.classList.add("mt-3");
    mediaElement.classList.add("justify-content-start");
    mediaElement.classList.add("align-items-center");
    mediaElement.classList.add("opacity-75");

    if (project.github) {
        let githubLink = document.createElement("a");
        githubLink.href = project.github;
        githubLink.target = "_blank";
        githubLink.classList.add("btn");
        githubLink.classList.add("btn-outline-dark");
        githubLink.classList.add("rounded-pill");
        githubLink.classList.add("px-3");
        githubLink.innerHTML = `<i class="fa-brands fa-github"></i>`;
        githubLink.title = "GitHub Repository";
        githubLink.rel = "noopener noreferrer";
        mediaElement.appendChild(githubLink);
    }
    if (project.youtube) {
        let youtubeLink = document.createElement("a");
        youtubeLink.href = project.youtube;
        youtubeLink.target = "_blank";
        youtubeLink.classList.add("btn");
        youtubeLink.classList.add("btn-outline-danger");
        youtubeLink.classList.add("rounded-pill");
        youtubeLink.classList.add("px-3");
        youtubeLink.innerHTML = `<i class="fa-brands fa-youtube"></i>`;
        youtubeLink.title = "YouTube Video";
        youtubeLink.rel = "noopener noreferrer";
        mediaElement.appendChild(youtubeLink);
    }
    if (project.liveDemo) {
        let liveDemoLink = document.createElement("a");
        liveDemoLink.href = project.liveDemo;
        liveDemoLink.target = "_blank";
        liveDemoLink.classList.add("btn");
        liveDemoLink.classList.add("btn-outline-warning");
        liveDemoLink.classList.add("px-3");
        liveDemoLink.classList.add("rounded-pill");
        liveDemoLink.title = "Live Demo";
        liveDemoLink.rel = "noopener noreferrer";
        liveDemoLink.innerHTML = `<i class="fa-solid fa-globe"></i>`;
        mediaElement.appendChild(liveDemoLink);
    }

    mediaContainer.appendChild(mediaElement);
});