class SidebarImpl extends HTMLElement {
    connectedCallback() {
        this.innerHTML = `
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Core</div>
                            <a class="nav-link" href="/">
                                <div class="sb-nav-link-icon"><i class="fas fa-house"></i></div>
                                Home
                            </a>
                            <a class="nav-link" href="index.html">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Dashboard
                            </a>
                            <a class="nav-link" href="userslist.html">
                                <div class="sb-nav-link-icon"><i class="fa-solid fa-user-tie"></i></div>
                                Manage Users
                            </a>
                            
                            <div class="sb-sidenav-menu-heading">Upload</div>
                            <a class="nav-link" href="uploadpoem.html">
                                <div class="sb-nav-link-icon"><i class="fa-regular fa-note-sticky"></i></div>
                                Upload Poem
                            </a>
                            <a class="nav-link" href="uploadproject.html">
                                <div class="sb-nav-link-icon"><i class="fa-solid fa-robot"></i></div>
                                Upload Project
                            </a>
                            <div class="sb-sidenav-menu-heading">Edit</div>
                            <a class="nav-link" href="poemslist.html">
                                <div class="sb-nav-link-icon"><i class="fa-regular fa-note-sticky"></i></div>
                                Modify Poems
                            </a>
                            <a class="nav-link" href="projectslist.html">
                                <div class="sb-nav-link-icon"><i class="fa-solid fa-robot"></i></div>
                                Modify Projects
                            </a>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Logged in as:</div>
                        <span id="usernamefooter"></span>
                    </div>
                </nav>
            </div>
        `;
    }
}
customElements.define('sb-impl', SidebarImpl);

async function logoutClick() {
    await logoutUser();
    localStorage.removeItem("token");
    localStorage.removeItem("rtoken");
    localStorage.removeItem("user");
    window.location.href = "/";
}

window.addEventListener('DOMContentLoaded', async (event) => {

    if(!localStorage.getItem("user") || !localStorage.getItem("token")) {
        window.location.href = "/dashboard/login.html";
        return;
    }

    const token = localStorage.getItem("token");
    let newData = await getUserData(token);

    if("error" in newData) {
        const newTokens = await refreshToken();
        if("error" in newTokens) {
            localStorage.removeItem("token");
            localStorage.removeItem("rtoken");
            localStorage.removeItem("user");
            window.location.href = "/dashboard/login.html";
            return;
        }

        localStorage.setItem("token", newTokens.token);
        localStorage.setItem("rtoken", newTokens.refreshToken);
        newData = await getUserData(newTokens.token);
    }

    localStorage.setItem("user", JSON.stringify(newData));
    document.getElementById("usernamefooter").innerText = newData.fullname;

    if(document.getElementById("welcomepoems")) {
        const dataResponse = await getHomeData();

        document.getElementById("username").innerText = newData.username;
        document.getElementById("welcomepoems").innerText = dataResponse.numberOfPoems;
        document.getElementById("welcomeprojects").innerText = dataResponse.numberOfProject;
    }

    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
            document.body.classList.toggle('sb-sidenav-toggled');
        }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }
});
