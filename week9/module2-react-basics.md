# Module 2: React Basics

## 1. Introduction to JSX and Component Architecture

1.  What is JSX?
    - JavaScript as XML (JSX) is a language extension.
    - It allows us to write tags (both HTML and XML) in plain JavaScript.
2.  How does it work?
    - Babel
      - Babel is a JavaScript transpiler that will convert extended JavaScript into ES5 JavaScript that will run in any browser
      - That said, Babel will take our JSX and transpile it over into standard JavaScript that the browser can interpret.
3.  What is a Component?
    - Components are building blocks for our UI.
    - They are simply a function that returns some HTML as JSX.

            function HelloWorld(){
                return (
                    <div>
                        <h1>Hello World from React!</h1>
                    </div>
                );
            }

      > Now when we want to render the component, we can simply say \<HelloWorld /> in our App component.
      >
      > Note: We can list the component as many times as we would like to as long as its wrapped in another tag.

    - Components are valuable for their reusability and their ability to manage their own independent state.

4.  Component Properties

    - "props" allow us to define the behavior of our components from elsewhere in the application.
    - Take our function from above and refactor to utilize props

            function HelloWorld(props){
                return (
                    <div>
                        <h1>Hello {props.name} from React!</h1>
                 </div>
                );
            }

      > Now when we want to render the component with props, we can simply say \<HelloWorld name="World" />
      >
      > This example will render the exact same as the above but it allows us to pass different names into the component
      > 
      > Note the similarities between React properties and HTML properties.

5.  Conditional Rendering

    - Now that we have the flexibility of passing different props into our components, we can now add some conditional rendering that will allow our component to render different elements based on some conditions that we define.
    - Take our above example HelloWorld component and lets add another property.
      > \<HelloWorld name="World" showHidden={true} />
    - Lets also include a second instance of our component that will pass false
      > \<HelloWorld name="World" showHidden={false} />
    - Now lets refactor our component to utilize this new property using conditional rendering.

            function HelloWorld(props){
                if(props.showHidden)
                    return (
                        <div>
                            <h1>Yahaha! You found me!</h1>
                        </div>);
                return (
                    <div>
                        <h1> Hello {props.name} from React!\</h1>
                    </div>
                );
            }

    - One thing to note is you will more commonly see inline conditional rendering utilizing ternary operators.

            function HelloWorld(props){
                return(
                    <div>
                        {props.showHidden ?
                        (<h1>Yahaha! You found me!</h1>) :
                        (<h1> Hello {props.name} from React!</h1>)}
                    </div>
                );
            }

6.  Working With Lists and Keys

    - Create an array with days of the week in a new file.
      const days = [
      "Sunday",
      "Monday",
      "Tuesday",
      "Wednesday",
      "Thursday",
      "Friday",
      "Saturday"
      ];
    - Now we can pass the list into a component via the properties
      > \<HelloWorld name="World" showHidden={false} days={days} />
    - In order to render each element in the array, we can utilize the map() function.

            function HelloWorld(props){
                if(props.showHidden)
                    return (
                        <div>
                            <h1>Yahaha! You found me!</h1>
                        </div>);
                return (
                    <div>
                        <h1> Hello {props.name} from React!\</h1>
                        <h3> Days I spend working:</h3>
                        <ul>
                            {props.days.map((day) => <li>{day}</li>)}
                        </ul>
                    </div>
                );
            }

    - Now as accurate as this may be to be working every day, lets utilize the filter() method to refine our array down to only business days

            function HelloWorld(props){
                if(props.showHidden)
                    return (
                        <div>
                            <h1>Yahaha! You found me!</h1>
                        </div>);
                return (
                    <div>
                        <h1> Hello {props.name} from React!\</h1>
                        <h3> Days I spend working:</h3>
                        <ul>
                            {props.days
                            .filter(day => day !== "Sunday" && day !== "Saturday")
                            .map((day) => <li>{day}</li>)}
                        </ul>
                    </div>
                );
            }

    - One thing you'll notice if you check the browser console is that we're receiving a warning that our list items do not have unique keys.
    - There are a few ways to resolve this, but the best option is typically to convert our string array into an array of objects that have a key and value pair.

            const daysArray = [
                    "Sunday",
                    "Monday",
                    "Tuesday",
                    "Wednesday",
                    "Thursday",
                    "Friday",
                    "Saturday"
            ];

            const days = daysArray.map((day, i) => ({id: i, title: day}));

    - Note that we adjusted the name of the initial array so that we can pass the same property into the component.
    - However, now that we are passing an object, we will need to do a bit of refactoring to make the component work.

            function HelloWorld(props) {
                if (props.showHidden)
                    return (
                    <div>
                        <h1>Yahaha! You found me!</h1>
                    </div>
                    );
                return (
                    <div>
                    <h1> Hello {props.name} from React!</h1>
                    <h3> Days I spend working:</h3>
                    <ul>
                        {props.days
                        .filter((day) => day.title !== "Sunday" && day.title !== "Saturday")
                        .map((day) => (
                            <li key={day.id}>{day.title}</li>
                        ))}
                    </ul>
                    </div>
                );
            }

    - As you've probably noticed by now, we have a lot of references to props and it is getting a bit tedious to write out and keep track of. Next we'll look at destructuring the props object to make things a bit cleaner.

7.  Destructuring

    1.  Array Destructuring

        - If we take another look at our daysArray on its own, as of right now, we can only access elements in the array by their index. If we wanted to access the "Sunday" element, it would look as follows:

                const daysArray = [
                        "Sunday",
                        "Monday",
                        "Tuesday",
                        "Wednesday",
                        "Thursday",
                        "Friday",
                        "Saturday"
                ];
                console.log(daysArray[0]);

        - What we can do instead, is utilize array destructuring to provide keys to an array during declaration.
        - Lets take a look at how we can access the "Sunday" element by supplying a key with destructuring.

                const [first] = [
                        "Sunday",
                        "Monday",
                        "Tuesday",
                        "Wednesday",
                        "Thursday",
                        "Friday",
                        "Saturday"
                ];
                console.log(first);

        - Notice that we no longer even have a name of the array to reference, we simply have the key that we can reference as its own variable.
        - It is important to note that the destructuring declaration does follow the order of the array, but we can leave gaps in the declaration if we need to.
        - Lets say I only needed access to the "Wendesday" element. I can write it as follows:

                const [ , , ,Humpday] = [
                        "Sunday",
                        "Monday",
                        "Tuesday",
                        "Wednesday",
                        "Thursday",
                        "Friday",
                        "Saturday"
                ];
                console.log("It is " + Humpday + " my dudes.");

        - As you can see, we leave spaces as placeholders for the other elements of the array.
        - Keep in mind that in order for us to convert the array to our objects with keys and values, we need to revert back to how it was before.

    2.  Object Destructuring

        - Up to this point, we have been referencing our properties using dot notation for every property that gets passed in.
        - This can get very tedious very quickly. So instead, lets utilize Object Destructuring as follows:

                function HelloWorld({name, showHidden, days}) {
                    if (showHidden)
                        return (
                        <div>
                            <h1>Yahaha! You found me!</h1>
                        </div>
                        );
                    return (
                        <div>
                        <h1> Hello {name} from React!</h1>
                        <h3> Days I spend working:</h3>
                        <ul>
                            {days
                            .filter((day) => day.title !== "Sunday" && day.title !== "Saturday")
                            .map((day) => (
                                <li key={day.id}>{day.title}</li>
                            ))}
                        </ul>
                        </div>
                    );
                }

        - You'll notice that we can now access these properties as variables directly without using dot notation.
        - This can make for a much cleaner code base that is easier to read and to understand.

8.  Quick mention of React Fragments
    - One thing to note in react is that in order to nest multiple components side by side, they need to be wrapped in the same tag.
    - You can use a \<div> tag to wrap your elements, but this leads to a lot of empty divs in the DOM.
    - Instead, we can utilize the \<React.Fragment> tag to perform the same use, but without affecting our DOM.
    - Even better, we can use the fragment shorthand of just \<></> to wrap our components.

## 2. React State Management

1.  Hooks

    - There are generally a few ways to work with State in React.
      - Redux - A more in depth but relatively complex predictable state container
      - Hooks - A more lightweight and modern approach
    - In this lesson, we will cover several different hooks that can help you to better manage your comonent states.

    1.  **useState** allows us to keep local state in a component

        - we can add useState to our imports from react as follows:
          > import React, { useState } from 'react';
        - useState is a function that returns an array of the following:
          - at index 0, we have the state object
          - at index 1, we have the function to use to update that object state
        - So with that in mind, we can make use of some array destructuring to get some variables we can work with
        - Lets make some changes to our example:

                function HelloWorld({ name, days }) {
                    const [today, updateToday] = useState("Monday");

                    return (
                        <>
                        <h1> Hello {name} from React!</h1>
                        <h3> Days I spend working:</h3>
                        <ul>
                            {days
                            .filter((day) => day.title !== "Sunday" && day.title !== "Saturday")
                            .map((day) => (
                                <li key={day.id}>{day.title}</li>
                            ))}
                        </ul>
                        <h4>Today is: {today}</h4>
                        <button onClick={() => updateToday("Friday")}>
                            Make it Friday Please.
                        </button>
                        </>
                    );
                }

        - As we can see, our application will load "Monday" as the inital value for today.
        - We can then press the Make it Friday button to update our state to friday using the function that we declared when assigning useState.
        - One thing to note is that typical naming conventions for useState is as follows:
          - const [name, updateName] = useState(initial state);

    2.  **useEffect** allows us to manage side-effects that arent related to rendering the component. Typically actions such as logging or fetching data will utilize useEffect.

        - we can add useEffect to our imports from react as follows:
          > import React, { useState, useEffect } from 'react';
        - useEffect takes in two parameters
          - a callback function denoting what action you want to perform
          - a dependency array of state values that act as triggers for the action on change
        - In our component, lets utilize useEffect to log the value of today to the console.

                function HelloWorld({ name, days }) {
                    const [today, updateToday] = useState("Monday");

                    useEffect(() => {
                        console.log(today);
                    }, [today]);

                    return (
                        <>
                        <h1> Hello {name} from React!</h1>
                        <h3> Days I spend working:</h3>
                        <ul>
                            {days
                            .filter((day) => day.title !== "Sunday" && day.title !== "Saturday")
                            .map((day) => (
                                <li key={day.id}>{day.title}</li>
                            ))}
                        </ul>
                        <h4>Today is: {today}</h4>
                        <button onClick={() => updateToday("Friday")}>
                            Make it Friday Please.
                        </button>
                        </>
                    );
                }

        - Now we can see that we write to the console every time the state value "today" changes.
        - If we keep clicking the button to change the state, note that we don't get anything logged to the console continuously. This is because the value was already set to "Friday" and as such is not changing.
        - Lets go ahead and add another state value to our component to track whether it is currently before or after noon and do some refactoring to utilize the new state

                function HelloWorld({ name, days }) {
                    const [today, updateToday] = useState("Monday");
                    const [afternoon, updateAfternoon] = useState(false);

                    useEffect(() => {
                        console.log(today);
                    }, [today]);

                    return (
                        <>
                        <h1> Hello {name} from React!</h1>
                        <h3> Days I spend working:</h3>
                        <ul>
                            {days
                            .filter((day) => day.title !== "Sunday" && day.title !== "Saturday")
                            .map((day) => (
                                <li key={day.id}>{day.title}</li>
                            ))}
                        </ul>
                        <h4>
                            It is currently {today} {afternoon ? "Afternoon" : "Morning"}
                        </h4>
                        <button onClick={() => updateToday("Friday")}>
                            Make it Friday Please.
                        </button>
                        <button onClick={() => updateAfternoon(!afternoon)}>
                            Toggle Time of Day.
                        </button>
                        </>
                    );
                }

        - In this example, we notice that even as we change the state of the afternoon variable, we are not logging anything to the console. This is because we only have the today variable listed in our depency array for useEffect.
        - If you wanted to, you could add the afternoon variable to the array, and the useEffect action will be triggered any time that either today, or afternoon's state changes.

    3.  **useReducer** allows us a better way of updating complex state logic

        - we can add useReducer to our imports from react as follows:
          > import React, { useState, useEffect, useReducer } from 'react';
        - useReducer takes two parameters, similarly to useState
          - the callback function with logic to update the state
          - the initial state of the object
        - Lets refactor our afternoon variable to utilize useReducer instead of useState.

                function HelloWorld({ name, days }) {
                    const [today, updateToday] = useState("Monday");
                    const [afternoon, updateAfternoon] = useReducer(
                        (afternoon) => !afternoon,
                        false
                    );

                    useEffect(() => {
                        console.log(today);
                    }, [today]);

                    return (
                        <>
                        <h1> Hello {name} from React!</h1>
                        <h3> Days I spend working:</h3>
                        <ul>
                            {days
                            .filter((day) => day.title !== "Sunday" && day.title !== "Saturday")
                            .map((day) => (
                                <li key={day.id}>{day.title}</li>
                            ))}
                        </ul>
                        <h4>
                            It is currently {today} {afternoon ? "Afternoon" : "Morning"}
                        </h4>
                        <button onClick={() => updateToday("Friday")}>
                            Make it Friday Please.
                        </button>
                        <button onClick={updateAfternoon}>Toggle Time of Day.</button>
                        </>
                    );
                }

        - As we can see here, useReducer is allowing us to supply the callback function to update the state, rather than writing it out in a different area.
        - This means that our onClick where we are updating the state becomes a lot cleaner.

## 3. Fetching Data

    1.  useState to set the initial state of the data.
    2.  useEffect to fetch the data and update the state.

            function App({ login }) {
                const [data, setData] = useState(null);

                useEffect(() => {
                    fetch(`https://api.github.com/users/${login}`)
                    .then((response) => response.json())
                    .then(setData);
                }, [login]);

                if (data) {
                    return (
                    <div>
                        <h1>{data.name}</h1>
                        <p>{data.location}</p>
                        <img alt={data.login} src={data.avatar_url} />
                    </div>
                    );
                }

                return <div>No User Found</div>;
            }
