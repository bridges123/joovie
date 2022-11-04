import {abbreviateNumber, performFollowersCount} from "../perform_data.js";


performFollowersCount('followers-count');

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