let timer = 1;
let elements = document.getElementsByClassName("blur-slide");

for (let el of elements) {
    setTimeout(() => {
        el.classList.add("show");
    }, timer * 400);

    timer++;
}

async function doAfterUnhide() {
    new Swiper(".poetry-swiper", {
        slidesPerView: 4,
        spaceBetween: 20,

        loop: true,

        autoplay: {
            delay: 2000,
        },

        navigation: {
            nextEl: "#poetry-next",
            prevEl: "#poetry-prev",
        },

        breakpoints: {
            0: {
                slidesPerView: 1
            },

            768: {
                slidesPerView: 2
            },

            1200: {
                slidesPerView: 4
            }

        }
    });

    new Swiper(".projects-swiper", {
        slidesPerView: 4,
        spaceBetween: 20,

        loop: true,

        autoplay: {
            delay: 2000,
        },

        navigation: {
            nextEl: "#proj-next",
            prevEl: "#proj-prev",
        },

        breakpoints: {
            0: {
                slidesPerView: 1
            },

            768: {
                slidesPerView: 2
            },

            1200: {
                slidesPerView: 4
            }

        }
    });
}

setTimeout(async () => {
    const hiddenElements = [...document.querySelectorAll('[hidden]')];
    for (let el of hiddenElements) {
        el.removeAttribute("hidden");

        await doAfterUnhide();
    }
}, (timer * 400) - 300);

(async function () {
    const dataResponse = await getHomeData();

    const poems = sortById(dataResponse.poetry);
    const projects = sortById(dataResponse.projects);

    document.getElementById("noofpoems").innerText = dataResponse.numberOfPoems;
    document.getElementById("noofprojects").innerText = dataResponse.numberOfProject;

    document.getElementById("poemgallary").innerHTML = `
        ${poems.map((poem, i) => {
            return `
            <div class="swiper-slide">
                <div class="card poetry-card h-100">
                    <div class="card-header">
                        <h5 class="card-title mb-0">${poem.title}</h5>
                    </div>

                    <div class="card-body text-center">
                        ${poem.content}
                        <br>
                        <div class="text-end">
                            --- ${poem.signature}
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
            `;
        }).join("")}
    `;

    document.getElementById("projectgallary").innerHTML = `
        ${projects.map((project, i) => {
            return `
            <div class="swiper-slide">
                <div class="card poetry-card h-100">
                    <div class="card-header">
                        <h5 class="card-title mb-0">${project.title}</h5>
                    </div>

                    <div class="card-body text-center">
                        <img src="${project.thumbnail}" alt="${project.title} thumbnail" class="project-thumbnail mb-3">
                        <br>
                        ${project.description}
                        <div class="text-start project-info">
                            <strong>Languages:</strong> ${project.languages}
                            <br>
                            <strong>Time Period:</strong> ${project.timeperiod}
                        </div>
                    </div>

                    <div class="card-footer">
                        <div class="row gx-0 align-items-center">
                            <div class="col-sm-4 text-start">
                                <a href="/project/?id=${project.id}" class="btn btn-primary">View</a>
                            </div>
                            <div class="col-sm-8 text-end">
                                ${project.date}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            `;
        }).join("")}
    `;
})();