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

window.addEventListener("DOMContentLoaded", async function () {
    if(localStorage.getItem("error")) {
        const error = localStorage.getItem("error");
        showTimedAlert(`Failed to open poem: ${error}`);
        localStorage.removeItem("error");
    }
    
    let poemsList = await getListOfPoems();
    if("error" in poemsList) {
        showTimedAlert(`Failed to load poems: ${poemsList.error}`);
        return;
    }

    poemsList = sortByDateAndId(poemsList);

    document.getElementById("gallery").innerHTML = `
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-3">
        ${poemsList.map((poem, i) => {
            return `
            <div class="col">
                <div class="mb-4" style="break-inside: avoid; max-width: 300px;">
                    <div class="card poetry-card h-100">
                        <div class="card-header">
                            <h5 class="card-title mb-0 text-center">${poem.title}</h5>
                        </div>

                        <div class="card-body text-center">
                            ${poem.content}
                            <br>
                            <div class="text-end">
                                ~ ${poem.signature}
                            </div>
                        </div>

                        <div class="card-footer">
                            <div class="row gx-0 align-items-center">
                                <div class="col-sm-4 text-start">
                                    <a href="/poem/?id=${poem.id}" class="btn btn-primary">Enter</a>
                                </div>
                                <div class="col-sm-8 text-end">
                                    ${poem.date}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            `;
        }).join("")}
        </div>
    `;
});