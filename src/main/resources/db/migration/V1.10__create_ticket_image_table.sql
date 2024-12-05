CREATE TABLE IF NOT EXISTS public.ticket_image
(
    id serial NOT NULL,
    key character varying NOT NULL,
    url character varying,
    incident_id integer NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (incident_id)
        REFERENCES public.incident (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
