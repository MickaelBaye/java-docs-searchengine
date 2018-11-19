package io.mibay.java.docs.javadocssearchengine.service.controllers;

import io.mibay.java.docs.javadocssearchengine.business.JavaDocsBusiness;
import io.mibay.java.docs.javadocssearchengine.model.JavaDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class JavaDocsController {

    private static final String REQUEST_MAPPING = "/java-docs";

    @Autowired
    private JavaDocsBusiness javaDocsBusiness;

    /**
     * TODO documentation
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = REQUEST_MAPPING, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JavaDoc save(@RequestBody JavaDoc entity) throws Exception {
        // FIXME Add HTTP code return management
        return javaDocsBusiness.save(entity);
    }

    /**
     * TODO documentation
     * @return
     */
    @RequestMapping(value = REQUEST_MAPPING, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<JavaDoc> findAll() {
        // FIXME Add HTTP code return management
        return javaDocsBusiness.findAll();
    }

    /**
     * TODO documentation
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = REQUEST_MAPPING + "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public JavaDoc findById(@PathVariable(name = "id") String id) throws Exception {
        // FIXME Add HTTP code return management
        return javaDocsBusiness.findById(id).orElse(null);
    }

    /**
     * TODO documentation
     * @param entity
     * @throws Exception
     */
    @RequestMapping(value = REQUEST_MAPPING + "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(JavaDoc entity) throws Exception {
        // FIXME Add HTTP code return management
        javaDocsBusiness.delete(entity);
    }

    /**
     * TODO documentation
     * @return
     */
    @RequestMapping(value = REQUEST_MAPPING, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, params = { "title" })
    public Iterable<JavaDoc> searchByTitle(@RequestParam(name = "title") String title) throws Exception {
        // FIXME Add HTTP code return management
        return javaDocsBusiness.searchByTitle(title);
    }

}
