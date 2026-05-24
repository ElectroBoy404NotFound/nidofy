function parsePoem(text) {
    return text
        .trim()
        .split(/\n\s*\n/)
        .filter(s => s.trim().length > 0)
        .map(stanza => {
            return stanza
                .split("\n")
                .map(line => line.trim())
                .filter(line => line.length > 0);
        });
}

async function onUploadPoemSubmit(e) {
    e.preventDefault();

    const title = document.getElementById("poemtitle").value;
    const signature = document.getElementById("poemsignature").value;
    const content = document.getElementById("poemcontent").value;
    const date = document.getElementById("poemdate").value;
    const parsedContent = parsePoem(content);

    console.log({
        title,
        signature,
        date,
        parsedContent
    });

    const result = await uploadPoem(title, signature, date, parsedContent);
    if("error" in result) {
        alert(`Failed to upload poem: ${result.error}`);
        return;
    }

    window.location.href = "index.html";
}