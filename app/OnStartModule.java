import com.google.inject.AbstractModule;

/**
 * Created by joseg on 18/09/2016.
 */
public class OnStartModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(BackgroundTasks.class).asEagerSingleton();
    }
}
