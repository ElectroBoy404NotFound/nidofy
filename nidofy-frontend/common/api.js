const API_BASE_URL = "http://localhost:8081/";

async function sendPOSTRequest(url, body, authtoken) {
    try {
        if(authtoken) {
            let response = await fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "signatureization": `Bearer ${authtoken}`
                },
                body: JSON.stringify(body)
            });
            let result = await response.json();
            return result;
        }

        let response = await fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(body)
        });
        let result = await response.json();
        return result;
    } catch (error) {
        return {
            message: "The post request failed!"
        };
    }
}

async function sendGETRequest(url, authtoken) {
    if(authtoken) {
        let response = await fetch(url, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "signatureization": `Bearer ${authtoken}`
            }
        });
        let result = await response.json();
        return result;
    }

    let response = await fetch(url, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        }
    });
    let result = await response.json();
    return result;
}

async function getHomeData() {
    const data = await sendGETRequest(`${API_BASE_URL}public/home/getHome`);
    return data;
}

async function getListOfPoems() {
    const data = await sendGETRequest(`${API_BASE_URL}public/poems/getAllPoems`);
    return data;
}

async function getListOfProjects() {
    const data = await sendGETRequest(`${API_BASE_URL}public/projects/getAllProjects`);
    return data;
}

async function getPoem(id) {
    const data = await sendGETRequest(`${API_BASE_URL}public/poems/getPoemById?id=${id}`);
    return data;
}

async function getProject(id) {
    const data = await sendGETRequest(`${API_BASE_URL}public/projects/getProjectById?id=${id}`);
    return data;
}