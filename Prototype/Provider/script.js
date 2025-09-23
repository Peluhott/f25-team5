const groups = [
    {
        groupName: "GreenDevs",
        groupPic: "../Customer/images/sample-poster-2.jpeg", 
        groupDescription: " open source development for the enviorment ",
        groupType: "Charity",
        memberCount: 8,
        people: ["Alice", "Bob", "Charlie"]
    },
    {
        groupName: "RedDevs",
        groupPic: "../Customer/images/sample-poster-1.jpeg", 
        groupDescription: " Team to compete in Hackathon downtown ",
        groupType: "Hackathon",
        memberCount: 5,
        people: ["David", "Eve", "Frank"]
    },
    {
        groupName: "BlueDevs",
        groupPic: "../Customer/images/sample-poster-7.jpeg", 
        groupDescription: " Game Development team for tower defence game",
        groupType: "Game",
        memberCount: 3,
        people: ["Grace", "Heidi"]
    },
    {
        groupName: "OrangeDevs",
        groupPic: "../Customer/images/sample-poster-4.jpeg", 
        groupDescription: " Study group for class 340",
        groupType: "Study",
        memberCount: 4,
        people: ["Ivan", "Judy", "Mallory"]
    }
]; // will fetch these when user logins
const peopleApplied = [
   {
        user: "Vicky",
        userPic: "../Customer/images/sample-pfp-2.jpeg",
        skills: "backend",
        team: "GreenDevs",
        message: "I have experience with environmental projects and love collaborating in teams."
    },
    {
        user: "Bob",
        userPic: "../Customer/images/sample-pfp-6.jpeg",
        skills: "frontend",
        team: "RedDevs",
        message: "I enjoy hackathons and have strong React skills. Ready to help the team win!"
    },
    {
        user: "Charlie",
        userPic: "../Customer/images/sample-pfp-4.jpeg",
        skills: "AI",
        team: "BlueDevs",
        message: "I’ve built several games with AI features and would love to join your project."
    }
];
const reviews = [
    {
        user: "Vicky",
        userPic: "../Customer/images/sample-pfp-2.jpeg",
        rating: 5,
        description: "Great teamwork and communication!",
        team: "Web Devs"
    },
    {
        user: "Bob",
        userPic: "../Customer/images/sample-pfp-6.jpeg",
        rating: 4,
        description: "Solid effort, learned a lot.",
        team: "Design Squad"
    },
    {
        user: "Charlie",
        userPic: "../Customer/images/sample-pfp-4.jpeg",
        rating: 3,
        description: "Project was challenging but rewarding.",
        team: "QA Crew"
    }
]

const GroupTypesIcons = {} // add photos for each group type here
const mainContainer = document.querySelector('.main-content');

function renderGroups() { // renders the groups user has
    mainContainer.innerHTML = '';

    if (groups.length == 0) { // if there are no groups it will have a button to create
        const groupCreate = document.createElement('div')
        groupCreate.className = 'group-create'
        
       
        const createText = document.createElement('p')
        createText.textContent = 'Create a Group'
        
        const createIcon = document.createElement('img')
        createIcon.src = '../../images/createbutton.png'
        createIcon.className = 'create-icon'
        
        groupCreate.appendChild(createText);
        groupCreate.appendChild(createIcon);
        
        mainContainer.appendChild(groupCreate)

        groupCreate.addEventListener('click', renderCreateGroupForm);
    } else {
        // Create a group container
        const groupContainer = document.createElement('div');
        groupContainer.className = 'group-container';

        for (let group of groups) { // make it into a for each loop
            const groupCard = document.createElement('div');
            groupCard.className = 'group-card';

            // Main group image at the top
            const groupPicture = document.createElement('img');
            groupPicture.src = group.groupPic;
            groupPicture.className = "group-image"
            groupCard.appendChild(groupPicture);

            // row right under image that will have pic of owner, name, and type
            const typeRow = document.createElement('div');
            typeRow.className = 'group-type-row';

            // left will have profile pic and name of person
            const typeLeft = document.createElement('div')
            typeLeft.className = 'left-header-group'
            const textLeft = document.createElement('p')
            textLeft.textContent = 'Jane Doe'
            const typePic = document.createElement('img');
            typePic.src = "../../images/mobile-05.jpg"
            typePic.className = 'group-type-pic';
            typeLeft.appendChild(typePic);
            typeLeft.appendChild(textLeft);
            typeRow.appendChild(typeLeft);

            // right will have type of group and maybe logo added later on
            const groupType = document.createElement('span');
            groupType.textContent = group.groupType;
            groupType.className = 'group-type-text';
            typeRow.appendChild(groupType);

            groupCard.appendChild(typeRow);
            
            // part under ownder name and type
            const groupTitle = document.createElement('h4');
            groupTitle.textContent = group.groupName;
            groupCard.appendChild(groupTitle);

            const groupDescription = document.createElement('p');
            groupDescription.textContent = group.groupDescription;
            groupCard.appendChild(groupDescription);

            if (group.state) { // add state to fake data to render this later on
                const groupState = document.createElement('p');
                groupState.textContent = group.state;
                groupCard.appendChild(groupState);
            }

            if (group.memberCount) { // just incase a user hasnt been added yet
                const memberCount = document.createElement('p');
                memberCount.textContent = `Members: ${group.memberCount}`;
                groupCard.appendChild(memberCount);
            }

            groupContainer.appendChild(groupCard);
        }

        // Create Group button at the end
        const groupCreate = document.createElement('div')
        groupCreate.className = 'group-create'
        
        // make this bold
        const createText = document.createElement('p')
        createText.textContent = 'Create a Group'
        // change this icon
        const createIcon = document.createElement('img')
        createIcon.src = '../../images/createbutton.png'
        createIcon.className = 'create-icon'
        
        groupCreate.appendChild(createText);
        groupCreate.appendChild(createIcon);
        
        groupContainer.appendChild(groupCreate);

        mainContainer.appendChild(groupContainer);

        groupCreate.addEventListener('click', renderCreateGroupForm);
    }
}

function renderReviews() {
    mainContainer.innerHTML = '';
    // if no reviews put no reviews have been made yet
    if (reviews.length === 0) {
        const noReviews = document.createElement('p');
        noReviews.textContent = 'No reviews made yet.';
        mainContainer.appendChild(noReviews);
    } else { // create cards for each review
        const reviewsContainer = document.createElement('div');
        reviewsContainer.className = 'reviews-container';

        reviews.forEach(review => {
            const reviewCard = document.createElement('div');
            reviewCard.className = 'review-card';

            // User profile picture
            const userPic = document.createElement('img');
            userPic.src = review.userPic || '';
            userPic.alt = `${review.user}'s profile picture`;
            userPic.className = 'review-user-pic';
            reviewCard.appendChild(userPic);

            // name of user
            const user = document.createElement('p');
            user.innerHTML = `<strong>User:</strong> ${review.user}`;
            reviewCard.appendChild(user);

            // rating that the user gave
            const rating = document.createElement('p');
            rating.innerHTML = `<strong>Rating:</strong> ${review.rating}`;
            reviewCard.appendChild(rating);

            // content of review
            const description = document.createElement('p');
            description.innerHTML = `<strong>Description:</strong> ${review.description}`;
            reviewCard.appendChild(description);

            // what team the user belonged to
            const team = document.createElement('p');
            team.innerHTML = `<strong>Team:</strong> ${review.team}`;
            reviewCard.appendChild(team);

            reviewsContainer.appendChild(reviewCard);
            // add button so that user can reply to review
        });

        mainContainer.appendChild(reviewsContainer);
    }
}



function renderCreateGroupForm() {
    mainContainer.innerHTML = '';
     
    // form to create a group need to decide later on what details to create with
    const form = document.createElement('form');
    form.className = 'create-group-form';
    // fix attributes to match the ones we use
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

function renderPeople() {
    mainContainer.innerHTML = '';

    if (peopleApplied.length === 0) {
        const noPeople = document.createElement('p');
        noPeople.textContent = 'No applicants at this time.';
        mainContainer.appendChild(noPeople);
    } else {
        const peopleContainer = document.createElement('div');
        peopleContainer.className = 'people-container';

        peopleApplied.forEach((person, index) => {
            const personCard = document.createElement('div');
            personCard.className = 'person-card';

            // Left: User profile picture
            const userPic = document.createElement('img');
            userPic.src = person.userPic;
            userPic.alt = `${person.user}'s profile picture`;
            userPic.className = 'person-user-pic';

            // Right: Info container
            const infoDiv = document.createElement('div');
            infoDiv.className = 'person-info';

            // User name
            const user = document.createElement('p');
            user.innerHTML = `<strong>User:</strong> ${person.user}`;
            infoDiv.appendChild(user);

            // Skills
            const skills = document.createElement('p');
            skills.innerHTML = `<strong>Skills:</strong> ${person.skills}`;
            infoDiv.appendChild(skills);

            // Team applied for
            const team = document.createElement('p');
            team.innerHTML = `<strong>Team:</strong> ${person.team}`;
            infoDiv.appendChild(team);

            // Message
            const message = document.createElement('p');
            message.innerHTML = `<strong>Message:</strong> ${person.message}`;
            infoDiv.appendChild(message);

            // Button container
            const btnContainer = document.createElement('div');
            btnContainer.className = 'person-btns';

            // Accept button
            const acceptBtn = document.createElement('button');
            acceptBtn.textContent = 'Accept';
            acceptBtn.className = 'accept-btn';
            acceptBtn.addEventListener('click', () => {
                const group = groups.find(g => g.groupName === person.team);
                if (group && !group.people.includes(person.user)) {
                    group.people.push(person.user);
                    group.memberCount = (group.memberCount || 0) + 1;
                }
                peopleApplied.splice(index, 1);
                renderPeople();
            });

            // Deny button
            const denyBtn = document.createElement('button');
            denyBtn.textContent = 'Deny';
            denyBtn.className = 'deny-btn';
            denyBtn.addEventListener('click', () => {
                peopleApplied.splice(index, 1);
                renderPeople();
            });

            btnContainer.appendChild(acceptBtn);
            btnContainer.appendChild(denyBtn);
            infoDiv.appendChild(btnContainer);

            personCard.appendChild(userPic);
            personCard.appendChild(infoDiv);

            peopleContainer.appendChild(personCard);
        });

        mainContainer.appendChild(peopleContainer);
    }
}

// add functionality to links in sidebar
document.getElementById('group-link').addEventListener('click', (e) => {
    e.preventDefault();
    renderGroups();
});

document.getElementById('people-link').addEventListener('click', (e) => {
    e.preventDefault();
    renderPeople();
});

document.getElementById('reviews-link').addEventListener('click', (e) => {
    e.preventDefault();
    renderReviews();
});



renderGroups() //initial render


