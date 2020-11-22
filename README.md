# Getting started
ArgonCore is a library based on [**Reactant**](https://reactant.dev/), make sure you get around with it first.
For reactant getting started part, please visit [reactant document](https://reactant.dev/docs/core/introduction).

If you are a Java developer, you may visit my [Reactant Java Example](https://github.com/clayclaw/ReactantJavaExample) first.
Well, this tutorial is basically for java developers.

# Register command
Follow [Reactant command tutorial](https://reactant.dev/docs/core/basic/command) and [example](https://github.com/clayclaw/ReactantJavaExample/blob/main/src/main/java/tech/clayclaw/reactantjavaexample/command/TestCommand.java) to create your command.
Then register with CommandBuilder in your component's onEnable method.
Here's an example:

```Java
@Component
public class MyCommandRegistry implements LifeCycleHook {

	@Inject
	private CommonCommandRegistryService commandService; // ArgonCore's command service
	
	@Override
	public void onEnable() {
		CommandBuilder.of(ReactantCommand)
			.subCommand(ReactantCommand) // use this if you want nested command
			.subCommand(ReactantCommand)
			.register(commandService);
	}
}
```

## Modular Data

ArgonCore has implemented [Reactant's ModularData](https://gitlab.com/reactant/modular-data/-/tree/master) and provided a [util](https://github.com/NuclearActive/ArgonCore/blob/master/src/main/java/tech/clayclaw/argoncore/javaadapter/util/ModularDataUtil.java) for it in order to have a better access to Bukkit's Persistent Data API.
For more information you may visit [mcbbs (Simplified Chinese)](https://www.mcbbs.net/forum.php?mod=viewthread&tid=918325) for more detail.

## I18n

ArgonCore will automatically generate locale content based on your default messages.

```Java
@Component
public class HelloWorldService implements LifeCycleHook {

	@Inject
	private I18nService i18nService; // ArgonCore's i18n service
	
	@Override
	public void onEnable() {
		LocaleTable.of(Locale.SIMPLIFIED_CHINESE)
			.locale("helloWorldString", "Hello World!") // tag, default content
			.locale("helloWorldString2", "World Hello!")
			.register(i18nService); // Register inside onEnable method

		System.out.println(i18nService.parse("helloWorldString")); // Hello World!
	}
}
```

If you want, you may use [Reactant's I18n service](https://reactant.dev/docs/core/basic/i18n) either.

## Event Listening

If you are using kotlin, I highly recommened you to use [Reactant's event service](https://reactant.dev/docs/core/basic/event-listener) which involves Rx Observable pattern.
ArgonCore will automatically register listener components so you don't have to do it, just simply **implement Listener** for your component. 

For example:
```Java
@Component
public class HelloWorldService implements Listener {
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		e.getPlayer().sendMessage("Hello!");
	}
}
```

## Bypass spigot's watchdog system
```Kotlin
bypassWatchDog {
	// actions
}
```
