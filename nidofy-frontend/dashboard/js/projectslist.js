
async function editproject(id) {
    localStorage.setItem("editProjectId", id);
    window.location.href = "editproject.html";
}

async function deleteproject(id, name) {
    if(!confirm(`Are you sure you want to delete this project (${name})? This action cannot be undone.`)) {
        return;
    }

    const result = await deleteProject(id);
    if("error" in result) {
        alert(`Failed to delete project: ${result.error}`);
        return;
    }
    window.location.reload();
}

(async () => {
    const projectsData = await getListOfProjects();
    if("error" in projectsData) {
        alert(`Failed to load projects data: ${projectsData.error}`);
        return;
    }

    const table = new simpleDatatables.DataTable("#poemstable", {
        data: {
            headings: ["ID", "Title", "Description", "Date", "Actions"],
            data: projectsData.map(project => [
                project.id,
                project.title,
                project.description,
                project.date,
                `<button onclick="editproject(${project.id})" class="btn btn-sm btn-sm-circle btn-success m-1"><i class="fa-solid fa-pen-to-square"></i></button>
                <button onclick="deleteproject(${project.id}, '${project.title}')" class="btn btn-sm btn-sm-circle btn-danger m-1"><i class="fa-solid fa-trash-can"></i></button>`
            ])
        }
    });
})();