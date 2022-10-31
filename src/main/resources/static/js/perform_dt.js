function div(a, b) {
    return (a - a % b) / b;
}

export function get_dt_counts(dateTime) {
    let diff = new Date() - dateTime;
    let seconds = div(diff, 1000);
    let minutes = div(seconds, 60);
    let hours = div(minutes, 60);
    let days = div(hours, 24);
    let months = div(days, 30);
    let years = div(months, 365);
    if (years) {
        if (years % 10 == 1)
            return `${years} год назад`;
        else if ([2, 3, 4].indexOf(years % 10) > -1)
            return `${years} года назад`;
        else
            return `${years} лет назад`;
    } else if (months) {
        if (months % 10 == 1)
            return `${months} месяц назад`;
        else if ([2, 3, 4].indexOf(months % 10) > -1)
            return `${months} месяца назад`;
        else
            return `${months} месяцев назад`;
    } else if (days) {
        if (days % 10 == 1)
            return `${days} день назад`;
        else if ([2, 3, 4].indexOf(days % 10) > -1)
            return `${days} дня назад`;
        else
            return `${days} дней назад`;
    } else if (hours) {
        if (hours % 10 == 1)
            return `${hours} час назад`;
        else if ([2, 3, 4].indexOf(hours % 10) > -1)
            return `${hours} часа назад`;
        else
            return `${hours} часов назад`;
    } else if (minutes) {
        if (minutes % 10 == 1)
            return `${minutes} минута назад`;
        else if ([2, 3, 4].indexOf(minutes % 10) > -1)
            return `${minutes} минуты назад`;
        else
            return `${minutes} минут назад`;
    } else if (seconds) {
        if (seconds % 10 == 1)
            return `${seconds} секунда назад`;
        else if ([2, 3, 4].indexOf(seconds % 10) > -1)
            return `${seconds} секунды назад`;
        else
            return `${seconds} секунд назад`;
    } else {
        return `только что`
    }
}