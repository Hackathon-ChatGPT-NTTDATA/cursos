package hackathon.nttdata.coderpath.cursos.redisson.test.config;

import java.util.Objects;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.config.Config;

public class RedissonConfig {
	
	private RedissonClient reddisonClient;

	public RedissonClient getClient() {

		if (Objects.isNull(this.reddisonClient)) {
			Config config = new Config();
			config.useSingleServer()
				.setAddress("redis://127.0.0.1:6379");
			reddisonClient = Redisson.create(config);
		}
		return reddisonClient;
	}

	public RedissonReactiveClient getReactiveClient() {
		return getClient().reactive();
	}

}
