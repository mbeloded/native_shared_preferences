package yeniellandestoy.native_shared_preferences;

import android.content.Context;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

/** NativeSharedPreferencesPlugin */
public class NativeSharedPreferencesPlugin implements FlutterPlugin, ActivityAware {
  private static final String CHANNEL_NAME = "native_shared_preferences";
  private MethodChannel channel;

  // This method is no longer needed, we will handle registration with onAttachedToEngine
  public static void registerWith(FlutterPluginBinding binding) {
    final NativeSharedPreferencesPlugin plugin = new NativeSharedPreferencesPlugin();
    plugin.setupChannel(binding.getBinaryMessenger(), binding.getApplicationContext());
  }

  @Override
  public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding binding) {
    // Register the plugin when the engine is attached
    setupChannel(binding.getBinaryMessenger(), binding.getApplicationContext());
  }

  @Override
  public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding binding) {
    // Cleanup when the plugin is detached from the engine
    teardownChannel();
  }

  @Override
  public void onAttachedToActivity(ActivityPluginBinding binding) {
    // Handle activity lifecycle if needed
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {
    // Handle activity lifecycle changes
  }

  @Override
  public void onDetachedFromActivity() {
    // Handle activity detached
  }

  @Override
  public void onReattachedToActivityForConfigChanges(ActivityPluginBinding binding) {
    // Handle reattached activity
  }

  private void setupChannel(BinaryMessenger messenger, Context context) {
    channel = new MethodChannel(messenger, CHANNEL_NAME);
    MethodCallHandlerImpl handler = new MethodCallHandlerImpl(context);
    channel.setMethodCallHandler(handler);
  }

  private void teardownChannel() {
    channel.setMethodCallHandler(null);
    channel = null;
  }
}
