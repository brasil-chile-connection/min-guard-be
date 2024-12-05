CREATE TABLE IF NOT EXISTS public.ticket_image
(
    id serial NOT NULL,
    key character varying NOT NULL,
    url character varying,
    ticket_id integer NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (ticket_id)
        REFERENCES public.ticket (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
