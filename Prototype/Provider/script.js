const groups = []
const people = []

const mainContainer = document.querySelector('.main-content');

function renderGroups(){
      mainContainer.innerHTML = ''

      if(groups.length == 0){
        const groupCreate = document.createElement('div')
        groupCreate.className = 'group-create'
        groupCreate.id = 'create-group-link'
        const createText = document.createElement('p')
        createText.textContent = 'Create a Group'
        const createIcon = document.createElement('img')
        createIcon.src = '../../images/icons8-create-64.png'
        createIcon.className = 'create-icon'
        groupCreate.appendChild(createText);
        groupCreate.appendChild(createIcon);
        mainContainer.appendChild(groupCreate)

        groupCreate.addEventListener('click', renderCreateGroupForm);
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

function renderMessages(){
  mainContainer.innerHTML = ''
  const text = document.createElement('p')
  text.innerText = 'hey this works'
  mainContainer.appendChild(text)
}

function renderSearch(){}

function renderCreateGroupForm() {
    mainContainer.innerHTML = '';

    const form = document.createElement('form');
    form.className = 'create-group-form';

    // Title
    const titleLabel = document.createElement('label');
    titleLabel.textContent = 'Title:';
    titleLabel.setAttribute('for', 'group-title');
    const titleInput = document.createElement('input');
    titleInput.type = 'text';
    titleInput.id = 'group-title';
    titleInput.required = true;

    // Description
    const descLabel = document.createElement('label');
    descLabel.textContent = 'Description:';
    descLabel.setAttribute('for', 'group-desc');
    const descInput = document.createElement('textarea');
    descInput.id = 'group-desc';
    descInput.required = true;

    // State
    const stateLabel = document.createElement('label');
    stateLabel.textContent = 'State:';
    stateLabel.setAttribute('for', 'group-state');
    const stateInput = document.createElement('input');
    stateInput.type = 'text';
    stateInput.id = 'group-state';
    stateInput.required = true;

    // Type
    const typeLabel = document.createElement('label');
    typeLabel.textContent = 'Type:';
    typeLabel.setAttribute('for', 'group-type');
    const typeInput = document.createElement('input');
    typeInput.type = 'text';
    typeInput.id = 'group-type';
    typeInput.required = true;

    // Image URL
    const imageLabel = document.createElement('label');
    imageLabel.textContent = 'Image URL:';
    imageLabel.setAttribute('for', 'group-image');
    const imageInput = document.createElement('input');
    imageInput.type = 'text';
    imageInput.id = 'group-image';
    imageInput.placeholder = 'Paste image URL here';
    imageInput.required = true;

    // Submit Button
    const submitBtn = document.createElement('button');
    submitBtn.type = 'submit';
    submitBtn.textContent = 'Create Group';

    // Append all to form
    form.appendChild(titleLabel);
    form.appendChild(titleInput);
    form.appendChild(document.createElement('br'));

    form.appendChild(descLabel);
    form.appendChild(descInput);
    form.appendChild(document.createElement('br'));

    form.appendChild(stateLabel);
    form.appendChild(stateInput);
    form.appendChild(document.createElement('br'));

    form.appendChild(typeLabel);
    form.appendChild(typeInput);
    form.appendChild(document.createElement('br'));

    form.appendChild(imageLabel);
    form.appendChild(imageInput);
    form.appendChild(document.createElement('br'));

    form.appendChild(submitBtn);

    mainContainer.appendChild(form);

    form.addEventListener('submit', function(e) {
        e.preventDefault();
        const newGroup = {
            title: titleInput.value,
            description: descInput.value,
            state: stateInput.value,
            type: typeInput.value,
            image: imageInput.value
        };

        groups.push(newGroup);
        renderGroups();
    });
}

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


