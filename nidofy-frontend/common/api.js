const API_BASE_URL = "http://localhost:8081/";

async function sendPOSTRequest(url, body, authtoken) {
    try {
        if(authtoken) {
            let response = await fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${authtoken}`
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
                "Authorization": `Bearer ${authtoken}`
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

async function login(email, password) {
    const data = await sendPOSTRequest(`${API_BASE_URL}userauth/login`, {
        email,
        password
    });
    return data;
}

async function getUserData(token) {
    const data = await sendGETRequest(`${API_BASE_URL}info/users/me`, token);
    return data;
}

async function refreshToken() {
    const data = await sendPOSTRequest(`${API_BASE_URL}userauth/refresh`, {
        "refreshToken": localStorage.getItem("rtoken")
    });

    return data;
}

async function uploadPoem(title, signature, date, stanzas) {
    const data = await sendPOSTRequest(`${API_BASE_URL}admin/poems/put`, {
        "title": title,
        "signature": signature,
        "date": date,
        "poem": stanzas
    }, localStorage.getItem("token"));
    return data;
}

async function uploadProject(title, description, languages, timeperiod, date, thumbnail, explanation, github, youtube, liveDemo) {
    const data = await sendPOSTRequest(`${API_BASE_URL}admin/projects/put`, {
        title,
        description,
        languages,
        timeperiod,
        date,
        thumbnail,
        explanation,
        github,
        youtube,
        liveDemo
    }, localStorage.getItem("token"));
    return data;
}