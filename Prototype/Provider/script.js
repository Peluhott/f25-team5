const groups = [
    {
        groupName: "GreenDevs",
        groupPic: "../Customer/images/sample-poster-2.jpeg", 
        groupDescription: "Open source development for the environment.",
        groupLongDescription: "GreenDevs is dedicated to building open source software that helps protect and improve the environment. We collaborate on projects that make a real-world impact, from climate data analysis to eco-friendly apps. Join us to code for a cause and make a difference together!",
        groupType: "Charity",
        memberCount: 8,
        state: "California",
        people: ["Alice", "Bob", "Charlie"],
        communication: [
            { type: "Discord", name: "GreenDevs Discord", url: "https://discord.gg/greendevs" },
            { type: "Slack", name: "GreenDevs Slack", url: "https://greendevs.slack.com" }
        ]
    },
    {
        groupName: "RedDevs",
        groupPic: "../Customer/images/sample-poster-1.jpeg", 
        groupDescription: "Team to compete in Hackathon downtown.",
        groupLongDescription: "RedDevs is a passionate team formed to participate in the upcoming downtown Hackathon. We focus on rapid prototyping, creative solutions, and teamwork. If you love coding under pressure and want to win, this is your squad!",
        groupType: "Hackathon",
        memberCount: 5,
        state: "Texas",
        people: ["David", "Eve", "Frank"],
        communication: [
            { type: "Discord", name: "RedDevs Hackathon", url: "https://discord.gg/reddevs" }
        ]
    },
    {
        groupName: "BlueDevs",
        groupPic: "../Customer/images/sample-poster-7.jpeg", 
        groupDescription: "Game Development team for tower defence game.",
        groupLongDescription: "BlueDevs is working on an exciting new tower defense game. We need artists, programmers, and designers to help bring our vision to life. Join us if you want to build something fun and learn about game development!",
        groupType: "Game",
        memberCount: 3,
        state: "New York",
        people: ["Grace", "Heidi"],
        communication: [
            { type: "Discord", name: "BlueDevs Game Dev", url: "https://discord.gg/bluedevs" }
        ]
    },
    {
        groupName: "OrangeDevs",
        groupPic: "../Customer/images/sample-poster-4.jpeg", 
        groupDescription: "Study group for class 340.",
        groupLongDescription: "OrangeDevs is a study group for students enrolled in class 340. We meet regularly to review material, work on assignments, and prepare for exams. All are welcome to join and collaborate!",
        groupType: "Study",
        memberCount: 4,
        state: "Florida",
        people: ["Ivan", "Judy", "Mallory"],
        communication: [
            { type: "GroupMe", name: "OrangeDevs Study", url: "https://groupme.com/orangedevs" }
        ]
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

const notifications = [
    {
        type: "Application",
        message: "User(name) has requested to join (blank) group"
    },
    {
        type: "Review",
        message: "User(name) has left a review for group(blank)"
    },
    {
        type: "Group",
        message: "User(name) has left group"
    },
    {
        type: "Application",
        message: "User(name) has requested to join (blank) group"
    },
    {
        type: "Application",
        message: "User(name) has requested to join (blank) group"
    },
    {
        type: "Review",
        message: "User(name) has left a review for group(blank)"
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

        groups.forEach((group, index) => {
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
            
            // part under owner name and type
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

            if (group.memberCount) { // just in case a user hasn't been added yet
                const memberCount = document.createElement('p');
                memberCount.textContent = `Members: ${group.memberCount}`;
                groupCard.appendChild(memberCount);
            }

            
            // Add click listener to groupCard
            groupCard.style.cursor = "pointer";
            groupCard.addEventListener('click', () => {
                renderGroupDetail(index);
            });

            groupContainer.appendChild(groupCard);
        });

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
        //event listener for create group button
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

    const form = document.createElement('form');
    form.className = 'create-group-form';

    // Group Name
    const nameLabel = document.createElement('label');
    nameLabel.textContent = 'Group Name:';
    nameLabel.setAttribute('for', 'group-name');
    const nameInput = document.createElement('input');
    nameInput.type = 'text';
    nameInput.id = 'group-name';
    nameInput.required = true;

    // Group Description
    const descLabel = document.createElement('label');
    descLabel.textContent = 'Short Description:';
    descLabel.setAttribute('for', 'group-desc');
    const descInput = document.createElement('textarea');
    descInput.id = 'group-desc';
    descInput.required = true;

    // Group Long Description
    const longDescLabel = document.createElement('label');
    longDescLabel.textContent = 'Long Description:';
    longDescLabel.setAttribute('for', 'group-long-desc');
    const longDescInput = document.createElement('textarea');
    longDescInput.id = 'group-long-desc';
    longDescInput.required = true;

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

    // Fake Upload Picture Button
    const picLabel = document.createElement('label');
    picLabel.textContent = 'Group Picture:';
    picLabel.setAttribute('for', 'group-pic');
    const picInput = document.createElement('input');
    picInput.type = 'file';
    picInput.id = 'group-pic';
    picInput.accept = 'image/*';
    //fake the upload

    // Communications (allow multiple)
    const commLabel = document.createElement('label');
    commLabel.textContent = 'Communications (type, name, url):';
    commLabel.setAttribute('for', 'group-comm');
    const commsDiv = document.createElement('div');
    commsDiv.id = 'group-comm';

    // Add initial communication row
    function addCommRow() {
        const commRow = document.createElement('div');
        commRow.className = 'comm-row';

        const commType = document.createElement('input');
        commType.type = 'text';
        commType.placeholder = 'Type (e.g. Discord)';
        commType.className = 'comm-type';

        const commName = document.createElement('input');
        commName.type = 'text';
        commName.placeholder = 'Name';
        commName.className = 'comm-name';

        const commUrl = document.createElement('input');
        commUrl.type = 'text';
        commUrl.placeholder = 'URL';
        commUrl.className = 'comm-url';

        const removeBtn = document.createElement('button');
        removeBtn.type = 'button';
        removeBtn.textContent = 'Remove';
        removeBtn.onclick = () => commRow.remove();

        commRow.appendChild(commType);
        commRow.appendChild(commName);
        commRow.appendChild(commUrl);
        commRow.appendChild(removeBtn);

        commsDiv.appendChild(commRow);
    }
    addCommRow();

    const addCommBtn = document.createElement('button');
    addCommBtn.type = 'button';
    addCommBtn.textContent = 'Add Communication';
    addCommBtn.onclick = addCommRow;

    // Submit Button
    const submitBtn = document.createElement('button');
    submitBtn.type = 'submit';
    submitBtn.textContent = 'Create Group';

    // Append all to form
    form.appendChild(nameLabel);
    form.appendChild(nameInput);
    form.appendChild(document.createElement('br'));

    form.appendChild(descLabel);
    form.appendChild(descInput);
    form.appendChild(document.createElement('br'));

    form.appendChild(longDescLabel);
    form.appendChild(longDescInput);
    form.appendChild(document.createElement('br'));

    form.appendChild(stateLabel);
    form.appendChild(stateInput);
    form.appendChild(document.createElement('br'));

    form.appendChild(typeLabel);
    form.appendChild(typeInput);
    form.appendChild(document.createElement('br'));

    form.appendChild(picLabel);
    form.appendChild(picInput);
    form.appendChild(document.createElement('br'));

    form.appendChild(commLabel);
    form.appendChild(commsDiv);
    form.appendChild(addCommBtn);
    form.appendChild(document.createElement('br'));

    form.appendChild(submitBtn);

    mainContainer.appendChild(form);

    form.addEventListener('submit', function(e) {
        e.preventDefault();

        // Get communications
        const comms = [];
        commsDiv.querySelectorAll('.comm-row').forEach(row => {
            const type = row.querySelector('.comm-type').value.trim();
            const name = row.querySelector('.comm-name').value.trim();
            const url = row.querySelector('.comm-url').value.trim();
            if (type && name && url) {
                comms.push({ type, name, url });
            }
        });

        

        const newGroup = {
            groupName: nameInput.value,
            groupPic: '../../Prototype/Customer/images/sample-poster-3.jpeg',
            groupDescription: descInput.value,
            groupLongDescription: longDescInput.value,
            groupType: typeInput.value,
            memberCount: 1,
            state: stateInput.value,
            people: [],
            communication: comms
        };

        groups.push(newGroup); 
        renderGroups();
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

function renderNotifications(){
    mainContainer.innerHTML = '';
    const notificationsContainer = document.createElement('div')
    notifications.forEach((notification, index) => {
        const notiContainer = document.createElement('div')
        notiContainer.className = 'noti-container'
        const notiType = document.createElement('p');
        // add class for this later
        notiType.innerText = `Type: ${notification.type}`
         //add class to notimessage later
        const notiMessage = document.createElement('p');
        notiMessage.innerText = notification.message;

        notiContainer.appendChild(notiType);
        notiContainer.appendChild(notiMessage);
        notificationsContainer.appendChild(notiContainer);
        mainContainer.appendChild(notificationsContainer)
    })
}

function renderGroupDetail(index) {
    mainContainer.innerHTML = '';
    const group = groups[index];

    const detail = document.createElement('div');
    detail.className = 'group-detail';

    // Title
    const title = document.createElement('h2');
    title.textContent = group.groupName;
    detail.appendChild(title);

    // State under title
    if (group.state) {
        const groupState = document.createElement('p');
        groupState.textContent = `State: ${group.state}`;
        groupState.className = 'group-state';
        detail.appendChild(groupState);
    }

    // Picture
    const picture = document.createElement('img');
    picture.src = group.groupPic;
    picture.alt = `${group.groupName} picture`;
    picture.className = 'group-detail-image';
    detail.appendChild(picture);

    // Long Description
    const longDesc = document.createElement('p');
    longDesc.textContent = group.groupLongDescription || group.groupDescription;
    longDesc.className = 'group-long-description';
    detail.appendChild(longDesc);

    // Team Communications
    if (group.communication && group.communication.length > 0) {
        const commHeader = document.createElement('h3');
        commHeader.textContent = 'Team Communications';
        detail.appendChild(commHeader);

        const commList = document.createElement('ul');
        group.communication.forEach(comm => {
            const commItem = document.createElement('li');
            if (comm.url) {
                const commLink = document.createElement('a');
                commLink.href = comm.url;
                commLink.target = '_blank';
                commLink.textContent = `${comm.type}: ${comm.name}`;
                commItem.appendChild(commLink);
            } else {
                commItem.textContent = `${comm.type}: ${comm.name}`;
            }
            commList.appendChild(commItem);
        });
        detail.appendChild(commList);
    }

    // Members
    if (group.people && group.people.length > 0) {
        const membersHeader = document.createElement('h3');
        membersHeader.textContent = 'Members';
        detail.appendChild(membersHeader);

        const membersList = document.createElement('ul');
        group.people.forEach(member => {
            const memberItem = document.createElement('li');
            memberItem.textContent = member;
            membersList.appendChild(memberItem);
        });
        detail.appendChild(membersList);
    }

    mainContainer.appendChild(detail);
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

document.getElementById('notification-link').addEventListener('click', (e) => {
    e.preventDefault();
    renderNotifications();
});

document.getElementById('home-page').addEventListener('click', (e) => {
    e.preventDefault();
    renderGroups();
});



renderGroups() //initial render


