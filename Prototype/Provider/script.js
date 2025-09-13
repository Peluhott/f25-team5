const groups = []
const people = []

const mainContainer = document.querySelector('.main-content');

function renderGroups(){
      mainContainer.innerHTML = ''

      if(groups.length == 0){
        const groupCreate = document.createElement('div')
        groupCreate.className = 'group-create'
        const createText = document.createElement('p')
        createText.textContent = 'Create a Group'
        const createIcon = document.createElement('img')
        createIcon.src = '../../images/icons8-create-64.png'
        createIcon.className = 'create-icon'
        groupCreate.appendChild(createText);
        groupCreate.appendChild(createIcon);
        mainContainer.appendChild(groupCreate)
      } else {
        for(let group of groups){
            const groupCard = document.createElement('div');
            groupCard.className = 'group-card';

            const groupPicture = document.createElement('img');
            groupPicture.src = group.image;
            groupCard.appendChild(groupPicture);

            const groupTitle = document.createElement('p');
            groupTitle = group.title;
            groupCard.appendChild(groupTitle);

            const groupSubTitle = document.createElement('p');
            groupTitle = group.subtitle;
            groupCard.appendChild(groupSubTitle);

            const groupTypeIcon = document.createElement('img');
            groupTypeIcon.src = group.type;
            groupCard.appendChild(groupTypeIcon);

            const groupHeartIcon = document.createElement('img');
            groupHeartIcon.src = group.image;
            groupCard.appendChild(groupHeartIcon);

            mainContainer.appendChild(groupCard);
        }
      }

}

function renderMessages(){}

function renderSearch(){}

document.getElementById('group-link').addEventListener('click', (e) => {
    e.preventDefault();
    renderGroups();
});

document.getElementById('message-link').addEventListener('click', (e) => {
    e.preventDefault();
    renderMessages();
});

document.getElementById('search-link').addEventListener('click', (e) => {
    e.preventDefault();
    renderSearch();
});

renderGroups()


