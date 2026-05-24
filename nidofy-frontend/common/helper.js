function sortById(array) {
    array.sort((a, b) => b.id - a.id);
    return array;
}