# React

1.  What is React? Is it a library or framework? What’s the difference between those?
    
    React is technically a JavaScript library. Generally, libraries are used to perform specific operations in your application, whereas frameworks provide a skeleton for the entire application to function in a specific way. Frameworks are typically much more dependent on abstractions and provide generic functionality out of the box.
    
2.  What does it mean to be component-based? What does a component represent?

    Component-based refers to an application design process that breaks the application down into reusible components. A component represents a building block for the UI and can be functional or logical by design.
    
3.  Tell me how you would start up a new React project? What does ‘create react app’ setup for you?

    You could run the command 'npx create-react-app [application name]'. Out of the box, create-react-app provides us with some boilerplating for a base level react app. It configures our project hierarchy, connects our App.js component to the DOM through the index.js ReactDOM.Render method and also configures our package.json with some defaults.
    
4.  How would you create a component?

    If you were creating function components in JSX, you could create a js file and in that file write 'export function ComponentName(){}'. Inside the brackets you would write whatever logic you want the component to perform. Typically returning some HTML as JavaScript thanks to JSX.
    
5.  What are “props”? What is state? What data should be put in which?
    
6.  What is the lifecycle of a component?

    1. Mounting
      * constructor
      * render
      * componentDidMount
    2. Updating
      * render
      * componentDidUpdate
    3. Unmounting
      * componentWillUnmount
    
7.  What is the difference between a functional and a class component?

    The difference is purely in its declaration. Both methods produce the same result.
    1. A functional declaration
        * function FunctionalComponent(){ return \<h1>some text\</h1>;}
    2. A class declaration
        * class ClassComponent extends React.Component {render(){return \<h1>some text\</h1> }}
    
8.  What is the purpose of a container component? What about a higher-order component?

    1. Container Component
    2. High-Order Component
    
9.  What is the virtual DOM?
    
10.  What is routing and how would you do routing in React?
    
11.  What is JSX? What does it compile into? How to include JS code in JSX?
    
12.  How do you handle events in React?
    
13.  Does React have 1-way or 2-way data binding and data flow?
    
14.  If a parent component has data that a child component needs to access, what should you do?
    
15.  If you have state in two child components that a parent component needs access to, what is a good solution for that?
    
16.  How do you do conditional rendering?
    
17.  What should you remember to include as a prop for lists of elements?
    
18.  What are some pros/cons of using TypeScript in a React application?
    
19.  Why is immutable state important?
    
20.  How would you handle forms and submitting forms with React?
    
21.  How do you recommend making an AJAX call in React? Which library have you used? Why not use fetch directly?
    
22.  How do you test React components and code that you write?
    
23.  What are some options for styling your React components?
    
24.  What is SASS? How is it different from CSS?