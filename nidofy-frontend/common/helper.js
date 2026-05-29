function parseDate(dateStr) {
    return new Date(dateStr.replace(/(\d+)(st|nd|rd|th)/, "$1"));
}

function sortByDateAndId(array) {
    return array.sort((a, b) => {
        const dateA = parseDate(a.date);
        const dateB = parseDate(b.date);

        const diff = dateB - dateA;
        if (diff !== 0) return diff;

        return b.id - a.id;
    });
}

const monthToNumber = {
    "January": 1,
    "February": 2,
    "March": 3,
    "April": 4,
    "May": 5,
    "June": 6,
    "July": 7,
    "August": 8,
    "September": 9,
    "October": 10,
    "November": 11,
    "December": 12
};

function dateStringToNumerialString(dateStr) {
    const nosuffix = dateStr.replace(/(\d+)(st|nd|rd|th)/, "$1");
    const split = nosuffix.split(" ");

    const day = parseInt(split[0]);
    const month = monthToNumber[split[1]];
    const year = parseInt(split[2]);

    return `${day.toString().padStart(2, "0")}-${month.toString().padStart(2, "0")}-${year}`;
}