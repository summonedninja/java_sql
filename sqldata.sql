--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: engine_a; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.engine_a (
    id integer NOT NULL,
    name character varying(50) NOT NULL,
    quality integer
);


ALTER TABLE public.engine_a OWNER TO postgres;

--
-- Name: engine_a_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.engine_a_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.engine_a_id_seq OWNER TO postgres;

--
-- Name: engine_a_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.engine_a_id_seq OWNED BY public.engine_a.id;


--
-- Name: engine_a id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.engine_a ALTER COLUMN id SET DEFAULT nextval('public.engine_a_id_seq'::regclass);


--
-- Data for Name: engine_a; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.engine_a (id, name, quality) FROM stdin;
4	stator_a	1
8	cilinder	2
9	ыефещк	2
1	compressor_b	1
\.


--
-- Name: engine_a_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.engine_a_id_seq', 9, true);


--
-- Name: engine_a engine_a_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.engine_a
    ADD CONSTRAINT engine_a_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

