package API.Service;

import API.Entity.Entity.Chateau;
import API.Utility.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ChateauService {

  Logger logger = LoggerFactory.getLogger(LoggingController.class);

    /**
     * Add New Chateau
     * @param chateau
     * @return
     */

    public Chateau saveNewChateau(Chateau chateau) {
        Chateau save = new Chateau();

        logger.info("save New Chateau = " + save.getName());
        return save;
    }
}
