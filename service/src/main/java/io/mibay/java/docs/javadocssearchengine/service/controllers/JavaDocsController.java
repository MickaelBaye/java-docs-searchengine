package io.mibay.java.docs.javadocssearchengine.service.controllers;

import io.mibay.java.docs.javadocssearchengine.business.JavaDocsBusiness;
import io.mibay.java.docs.javadocssearchengine.model.JavaDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class JavaDocsController {

    @Autowired
    private JavaDocsBusiness javaDocsBusiness;

//    /**
//     * TODO documentation
//     * @param entity
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(name = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public JavaDoc save(@RequestBody JavaDoc entity) throws Exception {
//        // FIXME Add HTTP code return management
//        return javaDocsBusiness.save(entity);
//    }
//
//    /**
//     * TODO documentation
//     * @return
//     */
//    @RequestMapping(name = "", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Iterable<JavaDoc> findAll() {
//        // FIXME Add HTTP code return management
//        return javaDocsBusiness.findAll();
//    }

    /**
     * TODO documentation
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/java-docs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public JavaDoc findById(@PathVariable(name = "id") String id) throws Exception {
        // FIXME Add HTTP code return management
        return javaDocsBusiness.findById(id).orElse(null);
    }

//    /**
//     * TODO documentation
//     * @param entity
//     * @throws Exception
//     */
//    @RequestMapping(name = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public void delete(JavaDoc entity) throws Exception {
//        // FIXME Add HTTP code return management
//        javaDocsBusiness.delete(entity);
//    }

}
