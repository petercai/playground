var dateArr = Object.values(list.reduce((result, {
    date,
    type,
    amount
}) => {
    // Create new group
    if (!result[date]) result[date] = {
        date,
        activities: []
    };
    // Append to group
    result[date].activities.push({
        type,
        amount
    });
    return result;
}, {}));


// Use Array.reduce to consolidate the list into a set of results, a plain object, grouped by date.
// The consolidate function destructure the item into three parameters.
// It then creates a new group if necessary.
// Current item's type and amount is then pushed to the group as part of an object literal.
// The same set is returned to the reduce, so that the next item will consolidate into the same set.
// Use Object.values to extract the values from the set. (Drop the keys)