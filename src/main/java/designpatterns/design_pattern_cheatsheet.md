# Design Patterns Interview Cheat Sheet

## 1. Singleton Pattern
- **Explanation:** Ensures a class has only one instance and provides a global access point.
- **When to Use:** Single shared resource, e.g., configuration manager, logger.
- **Structure:** Private constructor, static instance, static `getInstance()` method.
- **Real-world Analogy:** Like the president of a company — only one at a time.
- **Benefits:** Controlled access, avoids multiple instantiations.
- **Trade-offs:** Hard to unit test, introduces global state, may be overused.
- **When Not to Use:** If multiple instances might be needed or testing is a concern.
- **How to Break:**
  - Reflection: Can access private constructor to create multiple instances.
  - Serialization: Deserializing can create a new instance unless handled.
  - Cloning: Default `clone()` can produce another instance.
  - Multithreading: Without proper locking, multiple threads may create multiple instances.

## 2. Factory Method Pattern
- **Explanation:** Defines an interface for creating objects, letting subclasses decide which class to instantiate.
- **When to Use:** When a class cannot anticipate the type of objects it needs to create.
- **Structure:** Product interface, ConcreteProduct classes, Creator interface, ConcreteCreator classes.
- **Real-world Analogy:** Car factory producing different car models on demand.
- **Benefits:** Promotes loose coupling, easy to extend.
- **Trade-offs:** Adds extra classes, can complicate design.
- **When Not to Use:** If object creation is simple and unlikely to change.
- **How to Break:**
  - Hardcoding concrete classes instead of using the factory interface.
  - Overriding factory incorrectly or bypassing the factory.

## 3. Observer Pattern
- **Explanation:** One-to-many dependency; changes in subject notify all observers.
- **When to Use:** Event-driven systems, GUI updates, messaging systems.
- **Structure:** Subject maintains a list of observers and calls `notifyObservers()`.
- **Real-world Analogy:** Newsletter subscription — subscribers get notified of updates.
- **Benefits:** Decouples subject and observers, facilitates extensibility.
- **Trade-offs:** Memory leaks if observers aren’t removed; complexity with many observers.
- **When Not to Use:** One-to-one dependency or rare updates.
- **How to Break:**
  - Forgetting to unregister observers → memory leaks.
  - Direct tight coupling instead of using observer interface.

## 4. Strategy Pattern
- **Explanation:** Defines a family of interchangeable algorithms.
- **When to Use:** Multiple ways to perform a task, e.g., payment methods.
- **Structure:** Strategy interface, ConcreteStrategy classes, Context class.
- **Real-world Analogy:** Choosing different routes in a GPS navigation system.
- **Benefits:** Flexible algorithm selection, easy to extend.
- **Trade-offs:** More classes, adds complexity.
- **When Not to Use:** Behavior unlikely to change.
- **How to Break:**
  - Hardcoding algorithms instead of using strategy objects.
  - Not following interface → client dependent on concrete strategy.

## 5. Decorator Pattern
- **Explanation:** Dynamically adds responsibilities to objects without subclassing.
- **When to Use:** Flexible feature addition, e.g., GUI components, coffee toppings.
- **Structure:** Component interface, ConcreteComponent, Decorator abstract class, ConcreteDecorator.
- **Real-world Analogy:** Coffee shop — base coffee plus optional toppings.
- **Benefits:** Avoids subclass explosion, flexible extension.
- **Trade-offs:** Can complicate code, debugging harder.
- **When Not to Use:** Static features or simple objects.
- **How to Break:**
  - Overusing decorators → complex chains.
  - Subclassing instead of dynamic decoration → loses flexibility.

## 6. Adapter Pattern
- **Explanation:** Allows incompatible interfaces to work together.
- **When to Use:** Integrating legacy systems or third-party APIs.
- **Structure:** Target interface, Adaptee class, Adapter class implementing Target.
- **Real-world Analogy:** Power plug adapter for different sockets.
- **Benefits:** Enables reuse, decouples interfaces.
- **Trade-offs:** Adds layer, debugging harder.
- **When Not to Use:** Interfaces already compatible.
- **How to Break:**
  - Changing adaptee or target interface without updating adapter.
  - Direct use of incompatible classes bypassing adapter → tight coupling.

## 7. Command Pattern
- **Explanation:** Encapsulates requests as objects for parameterization and queuing.
- **When to Use:** Undo/redo operations, task scheduling, decoupled requests.
- **Structure:** Command interface, ConcreteCommand, Receiver, Invoker.
- **Real-world Analogy:** Remote control buttons sending commands to devices.
- **Benefits:** Decouples sender and receiver, flexible handling.
- **Trade-offs:** Many small classes, can increase complexity.
- **When Not to Use:** Simple direct calls suffice.
- **How to Break:**
  - Direct method calls instead of using commands → no decoupling.
  - Not storing commands for undo/redo → loses benefits.

## 8. Composite Pattern
- **Explanation:** Composes objects into tree structures; treats individual and composite objects uniformly.
- **When to Use:** Part-whole hierarchies, e.g., UI components, file systems.
- **Structure:** Component interface, Leaf, Composite class containing Components.
- **Real-world Analogy:** Folder containing files and subfolders.
- **Benefits:** Simplifies client code, uniform object treatment.
- **Trade-offs:** Design more abstract, can be overkill.
- **When Not to Use:** Shallow or simple hierarchies.
- **How to Break:**
  - Treating composite and leaf differently → breaks uniformity.
  - Flat structures when tree hierarchy is needed → loses scalability.

## 9. Chain of Responsibility Pattern
- **Explanation:** Passes requests along a chain of handlers; each decides to handle or pass.
- **When to Use:** Multiple potential handlers, e.g., support escalation.
- **Structure:** Handler abstract class, ConcreteHandler, successor chain.
- **Real-world Analogy:** Customer support escalation levels.
- **Benefits:** Decouples sender and receiver, flexible request handling.
- **Trade-offs:** Harder to debug, tracking flow is complex.
- **When Not to Use:** Single handler suffices.
- **How to Break:**
  - Hardcoding handler logic instead of chaining.
  - Skipping successor assignment → chain broken.

## 10. State Pattern
- **Explanation:** Allows object behavior to change when its internal state changes.
- **When to Use:** Complex state-dependent behavior, e.g., media player modes.
- **Structure:** State interface, ConcreteState classes, Context holding current state.
- **Real-world Analogy:** Traffic light changing behavior for cars.
- **Benefits:** Simplifies complex conditional logic, easier maintenance.
- **Trade-offs:** More classes, can be overkill for simple states.
- **When Not to Use:** Simple conditional behavior.
- **How to Break:**
  - Changing context behavior directly instead of using state objects.
  - Not updating the current state → behavior inconsistent.
