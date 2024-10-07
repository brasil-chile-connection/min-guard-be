CREATE TABLE IF NOT EXISTS public.users
(
    id serial NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    mobile_prefix character varying(16) NOT NULL,
    mobile_number character varying(32) NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    accept_tc boolean NOT NULL,
    gender_id integer NOT NULL,
    profile_picture_path text,
    role_id integer NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)
    );

CREATE TABLE IF NOT EXISTS public.role
(
    id serial NOT NULL,
    name character varying(30) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.gender
(
    id serial NOT NULL,
    name character varying(255) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.incident
(
    id serial NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    description character varying,
    title character varying(255) NOT NULL,
    location character varying(255) NOT NULL,
    urgency_id integer NOT NULL,
    reporter_id integer,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.status
(
    id serial NOT NULL,
    name character varying(100) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.urgency
(
    id serial NOT NULL,
    name character varying(100) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.incident_image
(
    id serial NOT NULL,
    image_path character varying NOT NULL,
    incident_id integer NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.ticket
(
    id serial NOT NULL,
    incident_id integer,
    title character varying(255) NOT NULL,
    location character varying(255) NOT NULL,
    description character varying,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    responsible_id integer,
    status_id integer NOT NULL,
    urgency_id integer NOT NULL,
    "identifier " uuid,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.comment
(
    id serial NOT NULL,
    message character varying NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    ticket_id integer NOT NULL,
    PRIMARY KEY (id)
    );

ALTER TABLE IF EXISTS public.users
    ADD FOREIGN KEY (gender_id)
    REFERENCES public.gender (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.users
    ADD FOREIGN KEY (role_id)
    REFERENCES public.role (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.incident
    ADD FOREIGN KEY (reporter_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.incident
    ADD FOREIGN KEY (urgency_id)
    REFERENCES public.urgency (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.incident_image
    ADD FOREIGN KEY (incident_id)
    REFERENCES public.incident (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.ticket
    ADD FOREIGN KEY (responsible_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.ticket
    ADD FOREIGN KEY (status_id)
    REFERENCES public.status (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.ticket
    ADD FOREIGN KEY (urgency_id)
    REFERENCES public.urgency (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.comment
    ADD FOREIGN KEY (ticket_id)
    REFERENCES public.ticket (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;
