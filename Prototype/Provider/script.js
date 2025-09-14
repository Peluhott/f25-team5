const groups = [] // will fetch these when user logins
const people = []
const reviews = [ // fake data to see what it looks like
    {
        user: "Alice",
        rating: 5,
        description: "Great teamwork and communication!",
        team: "Web Devs"
    },
    {
        user: "Bob",
        rating: 4,
        description: "Solid effort, learned a lot.",
        team: "Design Squad"
    },
    {
        user: "Charlie",
        rating: 3,
        description: "Project was challenging but rewarding.",
        team: "QA Crew"
    }
]

const mainContainer = document.querySelector('.main-content');

function renderGroups(){
      mainContainer.innerHTML = ''

      if(groups.length == 0){ // if you own no groups will give you option to create
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
        //if there are groups it renders the group
        // need to figure out the details that group will need this is temp
        // change this to a for each arrow function loop later
        for(let group of groups){
            const groupCard = document.createElement('div');
            groupCard.className = 'group-card';

            const groupPicture = document.createElement('img');
            groupPicture.src = group.image;
            groupCard.appendChild(groupPicture);

            const groupTitle = document.createElement('p');
            groupTitle.textContent = group.title;
            groupCard.appendChild(groupTitle);

            const groupDescription = document.createElement('p');
            groupDescription.textContent = group.description;
            groupCard.appendChild(groupDescription);

            const groupState = document.createElement('p');
            groupState.textContent = group.state;
            groupCard.appendChild(groupState);

            const groupType = document.createElement('p');
            groupType.textContent = group.type;
            groupCard.appendChild(groupType);

            mainContainer.appendChild(groupCard); // adds the group card
            
        }
        //After the loop is complete it will still render create button
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
      }

}

function renderMessages() {
    mainContainer.innerHTML = '';

    if (reviews.length === 0) {
        // if no reviews renders the message
        const noReviews = document.createElement('p');
        noReviews.textContent = 'No reviews made yet.';
        mainContainer.appendChild(noReviews);
    } else {

        //renders reviews in array
        const reviewsContainer = document.createElement('div');
        reviewsContainer.className = 'reviews-container';

        reviews.forEach(review => {
            const reviewCard = document.createElement('div');
            reviewCard.className = 'review-card';

            const user = document.createElement('p');
            user.innerHTML = `<strong>User:</strong> ${review.user}`;

            const rating = document.createElement('p');
            rating.innerHTML = `<strong>Rating:</strong> ${review.rating}`;

            const description = document.createElement('p');
            description.innerHTML = `<strong>Description:</strong> ${review.description}`;

            const team = document.createElement('p');
            team.innerHTML = `<strong>Team:</strong> ${review.team}`;

            reviewCard.appendChild(user);
            reviewCard.appendChild(rating);
            reviewCard.appendChild(description);
            reviewCard.appendChild(team);

            reviewsContainer.appendChild(reviewCard);
        });

        mainContainer.appendChild(reviewsContainer);
    }
}

function renderSearch(){} 

function renderCreateGroupForm() {
    mainContainer.innerHTML = '';
     
    // form to create a group need to decide later on what details to create with
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
    form.appendChild(document.createElement('br')); //space

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
        e.preventDefault(); // prevents default action which is to make a request online
        const newGroup = {
            title: titleInput.value,
            description: descInput.value,
            state: stateInput.value,
            type: typeInput.value,
            image: imageInput.value
        };

        groups.push(newGroup); 
        renderGroups(); //rerenders group page
    });
}

// add functionality to links in sidebar
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



renderGroups() //initial render


