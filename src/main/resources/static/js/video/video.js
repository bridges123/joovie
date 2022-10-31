function abbreviateNumber(value) {
    let newValue = value;
    if (value >= 1000) {
        const suffixes = ['', 'тыс.', 'млн.', 'млрд.', 'трлн.'];
        const suffixNum = Math.floor(('' + value).length / 3);
        let shortValue = '';
        for (let precision = 2; precision >= 1; precision--) {
            shortValue = parseFloat((suffixNum !== 0 ? (value / Math.pow(1000, suffixNum)) : value).toPrecision(precision))
            const dotLessShortValue = (shortValue + '').replace(/[^a-zA-Z 0-9]+/g, '');
            if (dotLessShortValue.length <= 2) {
                break
            }
        }
        if (shortValue % 1 !== 0) shortValue = shortValue.toFixed(1)
        newValue = shortValue + ' ' + suffixes[suffixNum]
    }
    return newValue;
}

const userFollowersCount = document.getElementById('followers-count');
const FC = userFollowersCount.textContent;
const newFC = abbreviateNumber(FC);
if (FC && newFC) {
    if (FC > 999 || !(FC % 100))
        userFollowersCount.textContent = `${newFC} подписчиков`
    else if (FC % 10 == 1)
        userFollowersCount.textContent = `${newFC} подписчик`;
    else if ([2, 3, 4].indexOf(FC % 10) > -1)
        userFollowersCount.textContent = `${newFC} подписчика`;
    else
        userFollowersCount.textContent = `${newFC} подписчиков`;
}

const videoViews = document.getElementById('video-views');
const VC = videoViews.textContent;
// add abreviate number by spaces (1 777 123 222)
// const newVC = abbreviateNumber(VC);
if (VC) {
    if (VC % 10 == 1)
        videoViews.textContent = `${VC} просмотр`;
    else if ([2, 3, 4].indexOf(VC % 10) > -1)
        videoViews.textContent = `${VC} просмотра`;
    else
        videoViews.textContent = `${VC} просмотров`;
}

const ccObj = document.getElementById("comments-count");
const CC = ccObj.textContent;
const newCC = abbreviateNumber(CC);
if (CC && newCC) {
    if (CC > 999 || !(CC % 100))
        ccObj.textContent = `${newCC} комментариев`
    else if (CC % 10 == 1)
        ccObj.textContent = `${newCC} комментарий`;
    else if ([2, 3, 4].indexOf(CC % 10) > -1)
        ccObj.textContent = `${newCC} комментария`;
    else
        ccObj.textContent = `${newCC} комментариев`;
}

const descObj = document.getElementById("description");
const desc = descObj.textContent;
let shortDesc = desc.slice(0, 190);
const moreButton = document.getElementById('more-button');
if (desc.length < 190 && moreButton.hidden) {
    moreButton.hidden = true;
} else {
    shortDesc += "...";
    moreButton.hidden = false;
}
descObj.textContent = shortDesc;


let isMore = false;
moreButton.onclick = function () {
    isMore = !isMore;
    if (isMore) {
        descObj.textContent = desc;
        this.textContent = "СВЕРНУТЬ";
    } else {
        descObj.textContent = shortDesc;
        this.textContent = "ЕЩЁ";
    }
};